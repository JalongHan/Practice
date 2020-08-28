package com.jlhan.basics.designmode.decorator.decorator;

import com.jlhan.basics.designmode.decorator.component.SwordsMan;

public abstract class Master extends SwordsMan {

    private SwordsMan mSwordsMan;

    public Master(SwordsMan mSwordsMan) {
        this.mSwordsMan = mSwordsMan;
    }

    @Override
    public void attackMagic() {
        mSwordsMan.attackMagic();
    }

}