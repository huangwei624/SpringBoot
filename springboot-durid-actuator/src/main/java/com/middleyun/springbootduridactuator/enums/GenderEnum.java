package com.middleyun.springbootduridactuator.enums;

/**
 * Create by huangwei on 2020/5/22 0022
 */

/**
 * 性别枚举
 */
public enum GenderEnum {
    MALE(1, "男"),
    MADAM(0, "女");

    private int code;
    private String description;

    GenderEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
