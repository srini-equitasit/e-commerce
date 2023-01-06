package com.equitasit.inventory_service.util;

public enum MsgConstants {


    INVENTORY_NOT_FOUND("101", "Inventory Found for given id"),
    IN_SUFFICIENT_INVENTORY("102", "In sufficient Inventory Inventory");


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
