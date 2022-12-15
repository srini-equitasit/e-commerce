package com.equitasit.product_service.util;

public enum MsgConstants {


    PRODUCT_NOT_FOUND("101", "Product Not Found for given id");

    private String code;

    private String msg;

    MsgConstants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
