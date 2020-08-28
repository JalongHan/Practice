package com.jlhan.basics.designmode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.basics.R;
import com.jlhan.basics.designmode.builder.builder.Builder;
import com.jlhan.basics.designmode.builder.concretebuilder.MoonComputerBuilder;
import com.jlhan.basics.designmode.builder.director.Director;
import com.jlhan.basics.designmode.factory.abstractfactory.AbstractComputerFactory;
import com.jlhan.basics.designmode.factory.factory.GDComputerFactory;
import com.jlhan.basics.designmode.factory.product.AsusComputer;
import com.jlhan.basics.designmode.factory.product.HpComputer;
import com.jlhan.basics.designmode.factory.product.LenovoComputer;

@Route(path = "/design/activity")
public class DesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        AbstractComputerFactory computerFactory = new GDComputerFactory();
        LenovoComputer lenovoComputer = computerFactory.createComputer(LenovoComputer.class);
        lenovoComputer.start();
        HpComputer hpComputer = computerFactory.createComputer(HpComputer.class);
        hpComputer.start();
        AsusComputer asusComputer = computerFactory.createComputer(AsusComputer.class);
        asusComputer.start();
        // 建造者
        Builder builder = new MoonComputerBuilder();
        Director director = new Director(builder);
        // 组装
        director.createComputer("i7-6700", "华擎玩家至尊", "三星DDR4");
    }
}