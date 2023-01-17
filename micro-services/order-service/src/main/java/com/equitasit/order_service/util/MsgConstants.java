package com.equitasit.order_service.util;

public enum MsgConstants {


    ORDER_NOT_FOUND("101", "Order Not Found for given id");

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
