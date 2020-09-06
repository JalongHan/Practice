package com.jlhan.basics.designmode.builder.concretebuilder;

import com.jlhan.basics.designmode.builder.builder.Builder;
import com.jlhan.basics.designmode.builder.product.Computer;

public class MoonComputerBuilder extends Builder {
    private Computer mComputer = new Computer();

    @Override
    public void buildCpu(String cpu) {
        mComputer.setmCpu(cpu);
    }

    @Override
    public void buildMainboard(String mainboard) {
        mComputer.setmMainboard(mainboard);
    }

    @Override
    public void buildRam(String ram) {
        mComputer.setmRam(ram);
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}