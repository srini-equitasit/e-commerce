package com.equitasit.payment_service.util;

public enum MsgConstants {


    ACT_NOT_FOUND("101", "Account Not Found for given id"),
    ACT_IN_SUFFICIENT_FUNDS("102", "Account has insufficient funds");

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
