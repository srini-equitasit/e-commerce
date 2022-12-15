package com.equitasit.product_seller_service.util;

public enum MsgConstants {


    PRODUCT_SELLER_NOT_FOUND("101", "Product Seller Not Found for given id");

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
