package com.jlhan.basics.designmode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;
import com.jlhan.basics.designmode.observer.ObserverClient;

@CollectActivity(activityPath = Constants.DESIGN.ACTIVITY_PATH, activityName = Constants.DESIGN.ACTIVITY_NAME)
@Route(path = Constants.DESIGN.ACTIVITY_PATH)
public class DesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
//        AbstractComputerFactory computerFactory = new GDComputerFactory();
//        LenovoComputer lenovoComputer = computerFactory.createComputer(LenovoComputer.class);
//        lenovoComputer.start();
//        HpComputer hpComputer = computerFactory.createComputer(HpComputer.class);
//        hpComputer.start();
//        AsusComputer asusComputer = computerFactory.createComputer(AsusComputer.class);
//        asusComputer.start();
//        // 建造者
//        Builder builder = new MoonComputerBuilder();
//        Director director = new Director(builder);
//        director.createComputer("i7-6700", "华擎玩家至尊", "三星DDR4");
//        // 代理模式
//        ProxyClient.staticBuy();
        // 动态代理
//        ProxyClient.DynamicBuy();
        // 装饰模式
//        DecoratorClient.invoke();
        // 外观模式
//        FacedeClient.invoke();
        // 享元模式
//        FlyweightClient.invoke();
        // 策略模式
//        StrategyClient.invoke();
        // 模板方法模式
//        TemplateClient.invoke();
        // 订阅者模式
        ObserverClient.invoke();
    }
}