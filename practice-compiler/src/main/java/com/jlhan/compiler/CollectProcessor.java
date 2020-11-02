package com.jlhan.compiler;

import com.google.gson.Gson;
import com.jlhan.annotation.CollectActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/**
 * @author hanjinlong
 * @date 2020/10/23
 * @description 注解处理器, 收集activity的path和名字生成后给首页列表使用.
 */
public class CollectProcessor extends AbstractProcessor {

    public static final String DATA_FILE_NAME = "DATA.json";

    private Messager mMessager;
    private Types mTypeUtils;
    private Filer mFiler;
    private Elements mElementUtils;
    // 是否是app的module
    private boolean mIsApp;
    private CollectData mCollectData;

    // 初始化
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnv.getMessager();
        mTypeUtils = processingEnv.getTypeUtils();
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mIsApp = "true".equals(processingEnvironment.getOptions().get("isApp"));
        mCollectData = new CollectData();
        // 当app模块下没有注解不走process方法.直接读取注解数据
        if (mIsApp) {
            readAnnotationData();
            writeToFile();
        }
    }

    // 支持的java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // 支持的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(CollectActivity.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(CollectActivity.class);
        // 先将所有的注解保存起来.
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                // 打印错误日志
                error(element, "Only classes can be annotated with @%s", CollectActivity.class.getSimpleName());
                // 退出
                return true;
            }
            CollectActivity annotation = element.getAnnotation(CollectActivity.class);
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.ActivityName = annotation.activityName();
            activityInfo.ActivityPath = annotation.activityPath();
            mCollectData.activityList.add(activityInfo);
        }
        // 当app模块下有注解时走process方法.直接读取注解数据
        if (mIsApp) {
            readAnnotationData();
            writeToFile();
        } else {
            saveAnnotationData();
        }
        return false;
    }

    private void writeToFile() {
        StringBuilder builder = new StringBuilder();
        builder.append("package com.jlhan.collect;\n\n");
        builder.append("import com.jlhan.core.ActivityFactory;\n");
        builder.append("public class ActivityListHolder { \n");
        builder.append("public static void init() {\n");
        for (ActivityInfo info : mCollectData.activityList) {
            // 检查这个注解是否是一个类
            builder.append("ActivityFactory.addActivity(\"");
            builder.append(info.ActivityPath);
            builder.append("\",\"");
            builder.append(info.ActivityName);
            builder.append("\");\n");
        }
        builder.append("}\n");
        builder.append("}\n");
        try {
            JavaFileObject source = mFiler.createSourceFile("com.jlhan.collect.ActivityListHolder");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        note(">>> collect is finish... <<<");
    }

    private void readAnnotationData() {
        // 从ClassLoader中的urls中读取每个module生成的jar包.拿到里面生成的DATA.json
        // 这种方式只适合当前gradle版本,尝试过6.1时,打包时不会再执行gradle中createFullJarDebug,导致URLCalssloader中无法拿到jar包.
        ClassLoader loader = getClass().getClassLoader();
        try {
            if (loader instanceof URLClassLoader) {
                List<String> fileList = new ArrayList<>();
                URL[] urls = ((URLClassLoader) loader).getURLs();
                for (URL url : urls) {
                    String filePath = url.getFile();
                    File file = new File(filePath);
                    if (file.getName().endsWith(".jar") && file.exists()) {
                        ZipFile zipFile = new ZipFile(file);
                        ZipEntry entry = zipFile.getEntry(DATA_FILE_NAME);
                        if (entry == null) {
                            continue;
                        }
                        InputStream inputSteam = zipFile.getInputStream(entry);
                        final int bufferSize = 1024;
                        final char[] buffer = new char[bufferSize];
                        final StringBuilder out = new StringBuilder();
                        InputStreamReader in = new InputStreamReader(inputSteam, StandardCharsets.UTF_8);
                        for (; ; ) {
                            int rsz = in.read(buffer, 0, buffer.length);
                            if (rsz < 0) {
                                break;
                            }
                            out.append(buffer, 0, rsz);
                            fileList.add(out.toString());
                        }
                    }
                }
                if (fileList.size() > 0) {
                    for (String bindingString : fileList) {
                        CollectData collectData = new Gson().fromJson(bindingString, CollectData.class);
                        mCollectData.activityList.addAll(collectData.activityList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnnotationData() {
        try {
            FileObject file = mFiler.createResource(StandardLocation.CLASS_OUTPUT, "", DATA_FILE_NAME);
            Writer writer = file.openWriter();
            writer.write(new Gson().toJson(mCollectData));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void error(Element element, String s, String simpleName) {
        // 当使用这个error打印时会退出processor,导致打包也结束.
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(s, simpleName), element);
    }

    private void note(String sNote) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, sNote);
    }


}
