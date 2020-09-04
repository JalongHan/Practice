package com.jlhan.basics.annotation.runtime;

public class RuntimeAnnotationTest {
    @GET(value = "http://ip.taobao.com/59.108.54.37")
    public String getIpMsg() {
        return "";
    }

    @GET(value = "http://ip.taobao.com/")
    public String getIp() {
        return "";
    }
} 