package com.equitasit.delivery_service.util;

public enum MsgConstants {


    DELIVERY_INFO_NOT_FOUND("101", "Delivery Information Found for given id");

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
