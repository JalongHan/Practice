package com.jlhan.basics.designmode.factory.abstractfactory;

import com.jlhan.basics.designmode.factory.iproduct.Computer;

public abstract class AbstractComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}