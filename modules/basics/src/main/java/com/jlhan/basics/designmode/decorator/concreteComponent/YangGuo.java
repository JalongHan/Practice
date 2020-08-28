package com.jlhan.basics.designmode.decorator.concreteComponent;

import com.blankj.utilcode.util.LogUtils;
import com.jlhan.basics.designmode.decorator.component.SwordsMan;

public class YangGuo extends SwordsMan {
    @Override
    public void attackMagic() {
        // 杨过初始的武学是全真剑法
        LogUtils.i("杨过使用全真剑法");
    }
}