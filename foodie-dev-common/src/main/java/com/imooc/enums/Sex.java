package com.imooc.enums;

/**
 * 性别枚举
 */
public enum Sex {
    woman(0,"女"),man(1,"女"),secret(3,"保密");


    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
