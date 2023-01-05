package com.equitasit.payment_service.exception;


import com.equitasit.payment_service.util.MsgConstants;

public class PaymentException extends RuntimeException {

    private MsgConstants msgConstant;

    public MsgConstants getMsgConstant() {
        return msgConstant;
    }

    public void setMsgConstant(MsgConstants msgConstant) {
        this.msgConstant = msgConstant;
    }

    public PaymentException() {
    }

    public PaymentException(MsgConstants msgConstant) {
        super(msgConstant.getMsg());
        this.msgConstant = msgConstant;
    }

    public PaymentException(MsgConstants msgConstant, Exception e) {
        super(msgConstant.getMsg(), e);
        this.msgConstant = msgConstant;
    }
}
