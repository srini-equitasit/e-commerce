package com.equitasit.delivery_service.exception;


import com.equitasit.delivery_service.util.MsgConstants;


public class DeliveryException extends RuntimeException {

    private MsgConstants msgConstant;

    public MsgConstants getMsgConstant() {
        return msgConstant;
    }

    public void setMsgConstant(MsgConstants msgConstant) {
        this.msgConstant = msgConstant;
    }

    public DeliveryException() {
    }

    public DeliveryException(MsgConstants msgConstant) {
        super(msgConstant.getMsg());
        this.msgConstant = msgConstant;
    }

    public DeliveryException(MsgConstants msgConstant, Exception e) {
        super(msgConstant.getMsg(), e);
        this.msgConstant = msgConstant;
    }
}
