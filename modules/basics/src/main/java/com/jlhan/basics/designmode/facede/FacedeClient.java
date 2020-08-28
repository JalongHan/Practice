package com.jlhan.basics.designmode.facede;

import com.jlhan.basics.designmode.facede.facede.ZhangWuji;

/**
 * 外观模式
 * 使用场景:
 * 构建一个有层次结构的子系统时,使用外观模式定义子系统中每层的入口点.如果子系统之间是相互依赖的,
 * 则可以让其通过外观接口进行通信,减少子系统之间的依赖关系.
 * 子系统往往会因为不断的重构深化而变得越来越复杂,大多数的模式使用时也会产生很多很小的类,
 * 这给外部调用它们的用户程序带来了使用上的困难.我们可以使用外观类提供一个简单的接口,对外隐藏子系统的具体实现并隔离变化.
 * 当维护一个遗留的大型系统时,可能这个系统已经非常难以维护和拓展,但因为它含有重要的功能,
 * 所以新的需求必须依赖它,这时可以使用外观念类,为设计粗糙或者复杂的遗留代码提供一个简单的接口,
 * 让新系统和外观类交互,而外观类负责与遗留的代码进行交互.
 */
public class FacedeClient {
    public static void invoke() {
        ZhangWuji zhangWuji = new ZhangWuji();
        // 用乾坤大挪移
        zhangWuji.Qiankun();
        // 用七伤拳
        zhangWuji.QiShang();
    }
} 