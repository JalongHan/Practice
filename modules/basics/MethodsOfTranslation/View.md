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

**interpolator[引特伯累特er]**  
-插值器 一般动画或者表示值变化  
**ViscousFluidInterpolator[威斯克福录i得]**  
-粘性流体插值器    
