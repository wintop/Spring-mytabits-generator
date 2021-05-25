package net.hyjuki.smgen.base.common;

import java.io.Serializable;

/**
 * 统一的返回信息类
 */
public class HjkResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    HjkResponse(MessageData message, Object data) {
        setCodeMessage(message);
        this.data = data;
    }

    HjkResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    HjkResponse(MessageData message) {
        setCodeMessage(message);
    }

    public static HjkResponse success(Object data) {
        return new HjkResponse(MessageData.SUCCESS, data);
    }

    public static HjkResponse success(Object data, int total) {
        return new HjkResponse(MessageData.SUCCESS, new PageResult(data, total));
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
        return new HjkResponse(1, message, null);
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

    @Override
    public String toString() {
        return "ReportMessage{" + "code=" + code + ", message='" + message + '\'' +
                ", data=" + data + '}';
    }

    public static class PageResult {
        private Object data;
        private int total;

        public PageResult(Object data, int total) {
            this.data = data;
            this.total = total;
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
            return "PageResult{" + "data=" + data + ", total=" + total + '}';
        }
    }
}
