package com.jlhan.basics.designmode.builder.builder;

import com.jlhan.basics.designmode.builder.product.Computer;

public abstract class Builder {

    public abstract void buildCpu(String cpu);

    public abstract void buildMainboard(String mainboard);

    public abstract void buildRam(String ram);

    public abstract Computer create();
} 