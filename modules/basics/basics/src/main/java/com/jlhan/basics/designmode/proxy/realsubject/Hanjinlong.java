package com.jlhan.basics.designmode.proxy.realsubject;

import com.blankj.utilcode.util.LogUtils;
import com.jlhan.basics.designmode.proxy.subject.IShop;

public class Hanjinlong implements IShop {
    @Override
    public void buy() {
        LogUtils.i("购买");
    }
}