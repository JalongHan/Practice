##View提供的方法  
-getTop() : 获取View自身顶边到其父布局顶边的距离  
-getLeft() : 获取View自身顶边到其父布局左边的距离  
-getRight() : 获取View自身顶边到其父布局右边的距离  
-getBottom() : 获取View自身顶边到其父布局底边的距离  

##MontionEvent提供的方法  
-getX() : 获取点击事件距离控件左边的距离，即视图坐标  
-getY() : 获取点击事件距离控件顶部的距离，即视图坐标  
-getRawX() : 获取点击事件距离整个屏幕左边的距离，即绝对坐标  
-getRawY() : 获取点击事件距离整个屏幕顶边的距离，即绝对坐标  

##解析Scroller  
-三个构造方法,可以传入一个插值器Interpolator,不传有默认的ViscousFluidInterpolator  
-startScroll只是传进了参数startX和startY表示滑动起点,dx dy滑动距离,duration持续时间,需要调用invalidate()方法使重绘制开始  
-重绘会调用View的draw()方法,draw()方法会调用View的computeScroll()方法.scroller.computeScrollOffset()方法会计算currX和currY  
-然后调用父view来进行scrollTo,通过不断移动小距离来平滑移动.  
-Scroller不能直接实现View滑动,需要配合View的computeScroll方法,在这个方法中不断让View绘制,不断重复就形成弹性平滑的滑动  
**-其实我理解它和valueAnimator类似,可以拿到一些插值器的值来进行view的移动.**  

##Activity源码构成  
-setContentView()  
->getWindow().setContentView()(getWindw获得mWindow是指PhoneWindow)  
->PhoneWindow.setContentView  
->installDecor() 生成DecorView  
->generateDecor() DecorView 是Activity中的根View.是PhoneWindow的内部类,并继承了FrameLayout  
->installDecor()中的generateLayout(DecorView) 根据不同情况加载不同的布局给layoutResource  
->加载了R.layout.screen_title这个布局 两个布局 一个titleView  一个contentView  

##源码解析View事件的分发机制  
-dispatchTouchEvent(MotionEvent ev) -用来进行事件的分发  
-onInterceptTouchEvent(MotionEvent ev) -用来进行事件的拦截,在dispatchTouchEvent()中调用,View中没有该方法  
-onTouchEvent(MontionEvent ev) -用来处理点击事件,在dispacthTouchEvent()方法中调用.  
-MontionEvent 事件是 down开始 ->move多个  ->up结束

##View事件分发  
-点击屏幕时就产生点击事件,这个事件被封装成一个类:MontionEvent.  
->先传递给Activity,调用activity中的dispatchTouchEvent(),实际是Activity中的PhoneWindow,PhoneWindow再把事件处理交给DecorView.  
->DecorView再将事件交给根ViewGroup.  
->ViewGroup的dispacthTouchEvent  如果是DOWN起始事件说明是新事件序列,所以初始化状态.  
然后根据DOWN(说明是新的事件序列,将状态初始化)  
和mFirstTouchTarget(当前viewgroup是否拦截了事件,如果拦截了mFirstTouchTarget=null,这时触发ACTION_DOWN事件则会执行onInterceptTouchEvent(ev)方法,    
如果是MOVE,UP事件则不再执行onInterceptTouch,而直接设置intercepted=true  
;,没拦截交给子view处理mFristTouchTarget!=null,)    
和boolean disallowIntercept(主要是禁止拦截除DOWN以外的事件)  
来执行onInterceptTouchEvent(ev)  默认返回false,如果想拦截要重写并返回true.  
1.for循环遍历viewgroup的子元素,判断是否能接受点击,则交子元素来处理,是倒序遍历的,最上层子view往内层遍历.  
2.然后判断是否在子view范围或子view是否在播放动画,不符合则continue表示这个view不符合条件,去遍历下一个view  
3.dispatchTransformedTouchEvent() 有子view就调用子view的dispatchTouchEvent方法,如果没有调用 super.dispatchTouchEvent  
->ViewGroup是继承View的,View的dispatchTouchEvent.如果OnTouchListener不为null并且onTouch方法返回true,则表示事件被消费.  
->OnThouchEvent中-只要View的CLICKABLE和LONG_CLICKABLE有一个为true,就会返回true消费这个事件,在ACTION_UP事件中调用performClick()方法  
->performClick() 如果设置了点击事件,那么它的onClick方法就会执行.  

##View事件的传递  
-首先是一段伪代码  
(```)
    public boolean dispatchTouchEvent(MotionEvent ev){  
        boolean result = false;  
        if(onInterceptTouchEvent(ev)){  
            result = super.onTouchEvent(ev);  
        }else{  
            result = child.dispatchTouchEvent(ev);  
        }  
        return result;  
    }  
(```)  
由上而下的传递逻辑  
Activity  
->PhoneWindows  
->DecorView  
->ViewGroup  dispatchTouchEvent()  
如果该ViewGroup的onInterceptTouchEvent()返回true,表示它要拦截这个事件,就由这个ViewGroup的onTouchEvent()方法处理  
如果返回false 则表示它不拦截这个事件,则这个事件会交给它的子元素的dispatchTouchEvent()来处理,  
如此反复,如果传递给底层的View,  
因View是没有子View的 调用 View的dispatchTouchEvent()方法,由于View没有拦截方法,最终会调用View的onTouchEvent()  

onTouchEvent() 返回true,则事件由底层的View消耗,如果返回false则表示该View不做处理,则传递给父View的onTouchEvent处理,  
如果父View的onTouchEvent()依旧返回false,则继续传递给该父View的父View处理,如此反复.

##View的工作流程  
1.DecorView加载完成后  

**理解MeasureSpec**  
SpecMode有下面三种模式:  
-UNSPECIFIED: 不确定但无上限.(特殊的AT_MOST).未指定模式,View想多大就多大,父容器不做限制,一般用于系统内部的测量.  
-AT_MOST: 不确定但有上限.最大模式,对应于wrap_content属性,子View的最终大小就是父View指定的SpecSize值.并且子View的大小不能大于这个值.  
-EXACTLY: 确定.精确模式,对应于macth_parent 和具体的数值,父窗口测量出View所需的大小,就是SpecSize的值.  
每个View都持有一个MeasureSpec,里面保存该View的尺寸规格,通过makeMeasureSpec保存宽和高信息.  
通过getMode获取模式,getSize获取宽高.受自身的LayoutParams和父容器的MeasureSpec共同影响.  
**对应关系:**
match_parent-->EXACTLY  
wrap_content-->AT_MOST  

**View的Measure流程**  
1.View.onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
2.setMeasuredDimension(int measureWidth, int measureHeight) 设置View宽高的  
3.getDefaultSize(int size, int measureSpec) 根据不同的SpecMode值来返回不同的Size值.  
-对于 自定义View来说wrap_content和match_parent属性效果是一样的,所以需要重写onMeausre方法,对warp_content属性处理.  
4.getSuggestedMinimumWidth() 或 getSuggestedMinimumHeight:   
如果没设置背景,则取值mMinWidth,mMinHeight是可设置的,如果不指定,默认为0  
如果设置了背景,则取值max(mMinWidth,mBackground.getMinimumWidth()) 两个值的最大值.最小宽度和mBackground是drawable  

**ViewGroup的Measure流程**  
1.不只要测量自身,还要遍历地调用子元素的measure方法,没有onMeasure方法,但有measureChildren(),遍历子元素并调用measureChild:  
2.调用child.getLayouParams获取LayouParams属性,获取子元素的MeasureSPec并调用子元素的measure测量.  
3.getChildMeasureSpec():  
父 EXACTLY     子有值-EXACTLY 子match_parent-EXACTLY     子warp_content-AT_MOST  
父 AT_MOST     子有值-EXACTLY **子match_parent-AT_MOST**     子warp_content-AT_MOST  
父 UNSPECIFIED 子有值-EXACTLY 子match_parent-UNSPECIFIED 子warp_content-UNSPECIFIED  
需要注意的,如果父是AT_MOST子元素match和wrap属性是一样的都是AT_MOST,如果要解决,需要在LayoutParams属性为Wrap时指定一下默认值宽和高  

**UNSPECIFIED[安死拜C Fai德]**  
-未指定模式 MeasureSpec中常量,View想多大就多大,父容器不做限制.

**EXACTLY[一个塞te雷]**  
-精确模式 对应match_parent属性和具体的数值,父容器测量出View所需要的大小,也就是SpecSize的值.

**traversal[垂ver嗖]**  
-遍历  
-performTraversals() 执行遍历  

**rotation[肉tei审]**  
-旋转  

**pivot[配v特]**  
-中心点,支点  
-pivotX:旋转的支点X,一般在旋转动画的参数中使用.  

**alpha[阿尔法]**  
-透明度  

**interpolator[因特伯累特er]**  
-插值器 一般动画或者表示值变化  
**ViscousFluidInterpolator[威斯克福录i得]**  
-粘性流体插值器    
**generate[摘ne瑞特]**  
-使形成   
**dispatch[地丝拜吃]**  
-派遣,发送  
**disallow[地丝劳o]**  
-驳回,不允许,不接受  
**intercept[因特赛坡特]**  
-拦截,截断  
**panel[趴no]**  
-面板,仪表板   
**intrinsic[因春赛克]**  
-本质的,固有的   
**suggested[涩摘丝ted]**  
-推荐的,建议的   
**adjust[a just]**  
-调整,校准,适应,使...适合.  
**dimension[di们神]**  
-尺寸,标出尺寸,规格的.  
**force[four死]**  
-强制.使..  
**wide[外的]**  
-宽  