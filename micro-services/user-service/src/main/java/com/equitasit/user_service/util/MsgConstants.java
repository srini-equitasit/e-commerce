package com.equitasit.user_service.util;

public enum MsgConstants {


    USER_NOT_FOUND("101", "User Not Found for given id");

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
