package com.equitasit.user_service.exception;


import com.equitasit.user_service.util.MsgConstants;

public class UserException extends RuntimeException {

    private MsgConstants msgConstant;

    public MsgConstants getMsgConstant() {
        return msgConstant;
    }

    public void setMsgConstant(MsgConstants msgConstant) {
        this.msgConstant = msgConstant;
    }

    public UserException() {
    }

    public UserException(MsgConstants msgConstant) {
        super(msgConstant.getMsg());
        this.msgConstant = msgConstant;
    }

    public UserException(MsgConstants msgConstant, Exception e) {
        super(msgConstant.getMsg(), e);
        this.msgConstant = msgConstant;
    }
}
