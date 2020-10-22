package com.jlhan.basics.designmode.strategy.concreteStrategy;

import com.blankj.utilcode.util.LogUtils;
import com.jlhan.basics.designmode.strategy.strategy.FightingStrategy;

public class StrongRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        LogUtils.i("遇到了强大的对手,张无忌使用乾坤大挪移");
    }
}