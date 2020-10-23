package com.jlhan.compiler;

import com.jlhan.annotation.ActivityBean;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.tools.JavaFileObject;

/**
 * @author hanjinlong
 * @date 2020/10/23
 * @description ActivityBean注解的处理器
 */
public class ActivityBeanProcessor extends AbstractProcessor {

    private Messager mMessager;
    private Types mTypeUtils;
    private Filer mFiler;
    private Elements mElementUtils;

    // 初始化
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnv.getMessager();
        mTypeUtils = processingEnv.getTypeUtils();
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
    }

    // 支持的java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(ActivityBean.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ActivityBean.class);

        StringBuilder builder = new StringBuilder();
        builder.append("package com.jlhan.practice;\n\n");
        builder.append("public class ActivityListHolder { \n");
        builder.append("public static void init() {\n");


        for (Element element : elements) {
            // 检查这个注解是否是一个类
            if (element.getKind() != ElementKind.CLASS) {
                // 打印错误日志
                error(element, "Only classes can be annotated with @%s", ActivityBean.class.getSimpleName());
                // 退出
                return true;
            }
            ActivityBean annotation = element.getAnnotation(ActivityBean.class);
            String name = annotation.activityName();
            String path = annotation.activityPath();
            builder.append("ActivityFactory.addActivity(\"");
            builder.append(name);
            builder.append("\",\"");
            builder.append(path);
            builder.append("\");\n");


        }
        builder.append("}\n");
        builder.append("}\n");

        try {
            JavaFileObject source = mFiler.createSourceFile("com.jlhan.practice.ActivityListHolder");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
        mMessager.printMessage(Diagnostic.Kind.ERROR, ">>> analysisAnnotated is finish... <<<");
        return false;
    }

    private void error(Element element, String s, String simpleName) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(s, simpleName), element);
    }


}
