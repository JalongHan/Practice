package com.jlhan.basics.designmode.factory.factory;

import com.jlhan.basics.designmode.factory.iproduct.Computer;
import com.jlhan.basics.designmode.factory.product.AsusComputer;
import com.jlhan.basics.designmode.factory.product.HpComputer;
import com.jlhan.basics.designmode.factory.product.LenovoComputer;

public class ComputerFactory {
    public static Computer createComputer(String type) {
        Computer mComputer = null;
        switch (type) {
            case "lenovo":
                mComputer = new LenovoComputer();
                break;
            case "hp":
                mComputer = new HpComputer();
                break;
            case "asus":
                mComputer = new AsusComputer();
                break;
            default:
                break;
        }
        return mComputer;
    }
}