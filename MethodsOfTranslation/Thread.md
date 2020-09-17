**4.1.2线程的状态:**  
1.New:新创建状态,还没有调用start方法,线程运行前还有一些基础工作要做.  
2.Runnable:可运行状态.一量调用start方法,线程就处于Runnable状态.一个可运行的线程可能正在运行也可能没有运行,这取决于操作系统给线程提供运行的时间.  
3.Blocked:阻塞状态.表示线程被锁阻塞,它暂时不活动.  
4.Waiting:等待状态.线程暂时不活动,并且不运行任何代码,这消耗最少的资源,直到线程调度器重新激活它.  
5.Time waiting:超时等待状态.和等待状态不同的是,它是可以在指定的时间自行返回的.  
6.Terminated:终止状态.表示当前线程已经执行完毕.导线线程终止有两种情况:  
(1).run方法执行完毕正常退出.  
(2).因为没有捕获异常而终止了run方法,导致线程进入终止状态.

**公平锁和非公平锁的**  
如果一个线程组里，能保证每个线程都能拿到锁，那么这个锁就是公平锁。相反，如果保证不了每个线程都能拿到锁，也就是存在有线程饿死，那么这个锁就是非公平锁  
可以看出非公平锁对锁的获取是乱序的，即有一个抢占锁的过程。  
优缺点：  
非公平锁性能高于公平锁性能。首先，在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。而且，非公平锁能更充分的利用cpu的时间片，  
尽量的减少cpu空闲的状态时间。  
使用场景  
使用场景的话呢，其实还是和他们的属性一一相关，  
举个栗子：如果业务中线程占用(处理)时间要远长于线程等待，那用非公平锁其实效率并不明显，但是用公平锁会给业务增强很多的可控制性。  

**volatile:**  
Java内存模型:  
局部变量,方法定义的参数不会在线程之间共享.线程之间的共享亦是存在主存中,每个线程都有一个私有的本地内存.  
java内存模型控制线程之间的通信,它决定一个线程对主存共享变量的写入何时对另一个线程可见.  
2种使用情景:  
1.在线程中的状态标志.  
2.单例模式中的双重检查模式.  

**阻塞队列**  
1.当队列中没有数据的情况下,消费者端的所有线程都会被自动阻塞(挂起),直到有数据放入队列.  
2.当队列中填满数据的情况下,生产者端的所有线程都会被自动阻塞(挂起),直接到队列中有空的位置,线程被自动唤醒.  
**BlockingQueue的核心方法**
放入数据:  
offer(anObject):如果可能将anObject加到BlockingQueue里,可以容纳返回true,否则返回false.不会阻塞当前执行方法的线程.  
offer(E o,long timeout,TimeUnit unit):可以设置等待时间,如果指定时间还不能加入到BlockingQueue,则返回失败.  
put(anObject):将anObject加到BlockingQueue里.如果没有空间,则调用此方法的线程被阻断,直到里面有空间再继续.  
获取数据:  
poll(time):取走BlockingQueue里排在首位的对象.若不能立即取出,则可以等time参数规定的时间,取不到时返回null.  
poll(long timeout,TimeUnit unit):从BlockingQueue中取出一个队首的对象,如果在指定时间内,队列有数据可取,  
则立即返回队列中排在首位的对象.直接到超时还没数据可取,返回失败.  
take():取走BlockingQueue里排在首位的对象.若BlockingQueue为空,则阻断进入等待状态,直到BLockingQueue有新的数据被加入.  
drainTo():一次性从BlockingQueue区取所有可用数据(还可指定获取的个数),通过该方法可以提升获取数据效率,无须多次加锁或释放锁.  
有七个阻塞队列:  
1.ArrayBlockingQueue:数组结构组成的有界阻塞队列.  
2.LinkedBlockingQueue:链表结构组成的有界阻塞队列.  
3.PriorityBlockingQueue:支持优先级排序的无界阻塞队列.  
4.DelayQueue:使用优先级队列pugmr无界阻塞队列.  
5.SynchronousQueue:不存储元素的阻塞队列.  
6.LinkedTransferQueue:由链表结构组成的无界阻塞队列.  
7.LinkedBlockingDeque:由链表结构组成的双向阻塞队列.  
**实现原理:**  

**使用场景:**  
生产者-消费者模式  

**线程池:**  
ThreadPoolExecutor:  
publick ThreadPoolExecutor(  
int corePoolSize,:核心xgtkovt.默认情况是空的,只有任务提交才会创建线程.如果当前运行的线程数少于corePoolSize,则创建新线程来处理任务.  
                  如果等于或多于corePoolSize,则不再创建.如果调用prestartAllcoreThread方法,线程池会提前创建并启动所有核心线程来等待任务.  
int maximumPoolSize,:线程池允许创建的最大线程数,如果任务队列满了并且线程数小于maximumPoolSize时,则线程池仍旧会创建新的线程来处理任务.  
long keepAliveTime,:非核心线程闲置的超时时间.超过这个时间则回收,如果任务很多,并且每个任务的执行事件很短,则可以调大keepAliveTime提高线程利用率  
                    另外,如果设置allowCoreThreadTimeOut属性为true时,keepAliveTime也会应用到核心线程上.    
TimeUnit unit,:keepAliveTime参数的时间单位  
BlockingQueue workQueue,:任务队列.如果当前线程数大于corePoolSize,则将任务添加到此任务队列中.是一个阻塞队列.    
ThreadFactory threadFactory,:线程工厂.可以用这个给每个创建出来的线程设置名字.一般无须设置该参数.    
RejectedExecutionHandler handler):饱和策略(拒绝执行handler).这时当前任务队列和线程池都满了时所采取的应对策略,默认是AbordPolicy,  
                                  表示无法处理新任务.并抛出异常.还有其它三种策略:  
                                   1.CallerRunsPolicy:用调用者所在的线程来处理任务.此策略提供简单的反馈控制机制,能够减缓新任务的提交速度.  
                                   2.DiscardPolicy:不能执行的任务,并将该任务删除.  
                                   3.DiscardOldestPolicy:丢弃队列最近的任务,并执行当前的任务.  

原理:  
提交任务 -> 线程是否达到corePoolSize -是-> 任务队列是否已满     -是-> 线程数是否达到最大线程数 -是-> 执行饱和策略.  
                                 -否-> 创建核心线程执行任务  -否-> 将任务加在任务队伍中    -否-> 创建非核心线程执行任务  

线程池的各类:  
1.FixedThreadPool: 
是可重用固定线程数的线程池.只有核心线程,并且数量是固定的,没有非核心线程.keepAliveTime为0L,意味着多余的线程会被终止.任务队列是LinkedBlockingQueue.  
核心线程不会被回收,当线程池有空闲线程,就去任务队列取任务,线程数超过corePoolSize时就将任务存任务队列里.  
2.CachedThreadPool:  
它的corePoolSize为0,maximumPoolSize为Integer.Max_VALUE,这意味着没有核心线程,非核心线程是无界的,keepAliveTime设置为60L,  
则空闲线程等待新任务的最长时间为60s,阻塞队列用的是SynchornousQueue,它是一个不存储元素的阻塞队列.  
因为是无界的,提交任务大于线程处理任务的速度就会不断创建新线程.每次任务都会立即有线程去处理,所以比较适合  
大量需要处理并且耗时较少的任务.  
3.SingleThreadExecutor:  
是使用单个工程线程的线程池,corePoolSize和maximumPoolSize都是1,意味着只有一个线程,其它参数与fixedThreadPool一样.  
4.ScheduledThreadPool:  
是一个能实现定时和周期性任务的线程池.  
使用DelayedWOrkQueue是无界的,所以maximumPoolSize这个参数是无效的.  
当执行它下面的scheduleAtFixedRate 或者 scheduleWithFixedDelay方法时,会向DelayedWorkQueue添加一个实现RunnableScheduleFutrue接口的  
ScheduledFutureTask(任务的包装类),并检查运行的线程是否达到corePoolSize.如果没有则新建线程并启动它,但并不是立即去执行任务,  
而是去queue中取scheduledFutureTask,然后去执行任务.如果线程达到corePoolSize,则将任务添加到queue中.  
DelayedWorkQueue会将任务进行排序,先要执行的任务放在队列的前面,当执行完任务后,会将ScheduledFutureTask中的time变量改为下次要执行的时间  
并放回到queue中.  

**AsyncTask原理**  
1.3.0版本之前的AsyncTask:  
使用的是ThreadPoolExecutor,它的核心线程是5个,线程池允许创建的最大线程数为128,非核心线程空闲等待新任务的最长时间为1s.  
采用的阻塞队列是LinkedBolckingQueue.它的缺点是最多容纳138个任务,当提交139个时,就会执行饱和策略,抛出RejectedExecutionException.  
2.7.0版本的AsyncTask:  
WorkerRunnable实现了Callable接口,并实现了它的call方法,在call方法中调用了doInBackground(mParams)来处理任务并得到结果,  
并最终调用postResult将结果投递出去.  
FurtrueTask是一个可管理的异步任务,它实现了Runnable和Futuree两个接口.因此可以包装Runnable和Callable.并提供给Executor执行.  
也可以调用线程直接执行FutureTask.run().
->当执行AsyncTask时要调用它的execute方法.它又调用executeOnExecutor方法.  
->再会先onPreExecute方法.
->mWorker.mParams = params:已知workerRnnable已经做为参数传递给了Future,因此参数被封装到FutureTask中.
->exec.execute():并将mFuture也就是前面的FutureTask传进去.exec就是sDefaultExecutor,它是一个串行的线程池SerialExecutor.
  SerialExecutor:调用exec.execute()时会将FutureTask加入到mTask中.当任务执行完成或当前没有活动任务都会执行scheduleNext,
  从mtask取出future任务交给THREAD_POOL_EXECUTOR处理.  

**implements[因普勒闷丝]**  
-在java中是实现的意思,本意 vt.执行，履行；贯彻，落实；使生效 n. 工具；器具；用具； 家具；服装；装备；手段；充当工具的人；【法律】履行(契约等)  
**interrupt[因特rua普特]**  
-中断,打断  
**volatile[wall了太累]**  
-翻译:挥发性的；不稳定的；爆炸性的；反复无常的  
-1.可见性:保证不同线程对这个变量操作的可见性,修改立即生效.  
-2.有序性:禁止进行指令重排.  
-3.单次读写的原子性 i=3.  
**reentrantLock[瑞恩春特lock]**  
-重进入锁  
**transfer[穿丝佛er]**  
-转让,转移  
**condition[肯低审]**  
-条件,情况  
**synchronized[sin 扣耐zi得]**  
-同步的.  
**synchronous[sin 扣耐si]**  
-同步的.同时的  
**offer[凹弗]**  
-提供,提议,试图.(放入数据用)  
**poll[泡~]**  
-投票.(取出数据)  
**unit[u内特]**  
-单位  
**priority[普ruai凹瑞tei]**  
-优先,优先权.  
**deque[戴克]**  
-双队列,双端队列  
**dequeue[地k ue]**  
-使()出列  
**enqueue[因k ue]**  
-使()出列  
**consumer[肯sue me]**  
-消费者,用户,顾客  
**producer[波低sir]**  
-生产者,制作人  
**rejected[re 摘克 ted]**  
-拒绝,驳回  
**execution[ai克丝 Q 审]**  
-执行,实行,完成;死刑  
**policy[波了 c]**  
-策略,方针;保险单.  
**discard[地丝卡得]**  
-抛弃,放弃,丢弃  
**fixed[费克斯得]**  
-确定的;固执的  
**serial[c 瑞凹]**  
-连续的;连载的.  
**schedule[晒酒o]**  
-计划(表);安排,预定;将....列入计划表或清单  