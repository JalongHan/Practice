package com.jlhan.basics.designmode.strategy.concreteStrategy;

import com.blankj.utilcode.util.LogUtils;
import com.jlhan.basics.designmode.strategy.strategy.FightingStrategy;

public class CommonRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        LogUtils.i("遇到了普通的对手,张无忌使用圣火令神功");
    }
}