package com.yantu.sbmm.common.util;

public class TestCompile {
    public static void main(String[] args) {
        int orig = 42 ;
        TestCompile x = new TestCompile();
        int y = x.go(orig);
        System.out.println("orig = [" + y + "]");
    }

    int go(int arg){
        arg = arg * 2;
        return  arg;
    }
}
