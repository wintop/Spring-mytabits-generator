package net.hyjuki.smgen.genconfig.base.utils;

import java.io.Serializable;

/**
 * 改进版的返回信息类
 */
public class HjkResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    private int total;

    HjkResponse(MessageData message, Object data, int total) {
        setCodeMessage(message);
        this.data = data;
        this.total = total;
    }

    HjkResponse(int code, String message, Object data, int total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }
    HjkResponse(MessageData message) {
        setCodeMessage(message);
    }

    public static HjkResponse success(Object data, int total) {
        return new HjkResponse(MessageData.SUCCESS, data, total);
    }

    public static HjkResponse success(Object data) {
        return new HjkResponse(MessageData.SUCCESS, data, 0);
    }


    public static HjkResponse success() {
        return new HjkResponse(MessageData.SUCCESS);
    }

    public static HjkResponse fail(MessageData messageData) {
        return new HjkResponse(messageData);
    }

    public static HjkResponse fail() {
        return new HjkResponse(MessageData.FAIL);
    }

    public static HjkResponse fail(String message) {
        return new HjkResponse(1, message, null, 0);
    }

    public void setCodeMessage(MessageData messageData) {
        this.code = messageData.getCode();
        this.message = messageData.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReportMessage{" + "code=" + code + ", message='" + message + '\'' +
                ", data=" + data + ", total=" + total + '}';
    }
}
