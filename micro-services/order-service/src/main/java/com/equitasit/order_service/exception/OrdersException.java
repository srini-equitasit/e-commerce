package com.equitasit.order_service.exception;


import com.equitasit.order_service.util.MsgConstants;


public class OrdersException extends RuntimeException {

    private MsgConstants msgConstant;

    public MsgConstants getMsgConstant() {
        return msgConstant;
    }

    public void setMsgConstant(MsgConstants msgConstant) {
        this.msgConstant = msgConstant;
    }

    public OrdersException() {
    }

    public OrdersException(MsgConstants msgConstant) {
        super(msgConstant.getMsg());
        this.msgConstant = msgConstant;
    }

    public OrdersException(MsgConstants msgConstant, Exception e) {
        super(msgConstant.getMsg(), e);
        this.msgConstant = msgConstant;
    }
}
