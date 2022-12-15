package com.equitasit.product_seller_service.exception;

import com.equitasit.product_seller_service.util.MsgConstants;

public class ProductSellerException extends RuntimeException {

    private MsgConstants msgConstant;

    public MsgConstants getMsgConstant() {
        return msgConstant;
    }

    public void setMsgConstant(MsgConstants msgConstant) {
        this.msgConstant = msgConstant;
    }

    public ProductSellerException() {
    }

    public ProductSellerException(MsgConstants msgConstant) {
        super(msgConstant.getMsg());
        this.msgConstant = msgConstant;
    }

    public ProductSellerException(MsgConstants msgConstant, Exception e) {
        super(msgConstant.getMsg(), e);
        this.msgConstant = msgConstant;
    }
}
