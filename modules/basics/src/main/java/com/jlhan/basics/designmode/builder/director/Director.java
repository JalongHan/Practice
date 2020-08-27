package com.jlhan.basics.designmode.builder.director;

import com.jlhan.basics.designmode.builder.builder.Builder;
import com.jlhan.basics.designmode.builder.product.Computer;

/**
 * 使用建造都 模式的场景和优缺点
 */
public class Director {
    Builder mBuilder = null;

    public Director(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public Computer createComputer(String cpu, String mainboard, String ram) {
        this.mBuilder.buildCpu(cpu);
        this.mBuilder.buildMainboard(mainboard);
        this.mBuilder.buildRam(ram);
        return mBuilder.create();
    }
}