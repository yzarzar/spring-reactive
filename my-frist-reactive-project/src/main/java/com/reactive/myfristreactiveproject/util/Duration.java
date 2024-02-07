package com.reactive.myfristreactiveproject.util;

public class Duration {
    public static void duration(int seconds) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
