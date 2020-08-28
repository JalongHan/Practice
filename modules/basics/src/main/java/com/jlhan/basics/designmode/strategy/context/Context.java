package com.jlhan.basics.designmode.strategy.context;

import com.jlhan.basics.designmode.strategy.strategy.FightingStrategy;

public class Context {
    private FightingStrategy fightingStrategy;

    public Context(FightingStrategy fightingStrategy) {
        this.fightingStrategy = fightingStrategy;
    }

    public void fighting() {
        fightingStrategy.fighting();
    }
}