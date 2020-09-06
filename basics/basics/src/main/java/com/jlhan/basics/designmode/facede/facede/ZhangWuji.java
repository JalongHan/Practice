package com.jlhan.basics.designmode.facede.facede;

import com.jlhan.basics.designmode.facede.subsystem.JingMai;
import com.jlhan.basics.designmode.facede.subsystem.NeiGong;
import com.jlhan.basics.designmode.facede.subsystem.ZhaoShi;

public class ZhangWuji {
    private JingMai jingMai;
    private ZhaoShi zhaoShi;
    private NeiGong neiGong;

    public ZhangWuji() {
        jingMai = new JingMai();
        zhaoShi = new ZhaoShi();
        neiGong = new NeiGong();
    }

    /**
     * 使用乾坤大挪移
     */
    public void Qiankun() {
        // 开启经脉
        jingMai.jingMai();
        // 使用内功乾坤大挪移
        neiGong.QianKun();
    }

    /**
     * 使用七伤拳
     */
    public void QiShang() {
        // 开启经脉
        jingMai.jingMai();
        // 使用内功九阳神功
        neiGong.JiuYang();
        // 使用七伤拳
        zhaoShi.QiShangQuan();
    }

}