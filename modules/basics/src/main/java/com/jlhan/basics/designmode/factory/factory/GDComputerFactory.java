package com.jlhan.basics.designmode.factory.factory;

import com.jlhan.basics.designmode.factory.abstractfactory.AbstractComputerFactory;
import com.jlhan.basics.designmode.factory.iproduct.Computer;

public class GDComputerFactory extends AbstractComputerFactory {
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String className = clz.getName();
        try {
            computer = (Computer) Class.forName(className).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}