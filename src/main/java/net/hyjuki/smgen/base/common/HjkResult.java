package net.hyjuki.smgen.base.common;

import java.io.Serializable;
import java.util.List;

/**
 * 统一的返回信息类，带类型限制
 */
public class HjkResult<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    HjkResult(MessageData message, T data) {
        setCodeMessage(message);
        this.data = data;
    }

    HjkResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    HjkResult(MessageData message) {
        setCodeMessage(message);
    }

    //
    public static <T> HjkResult<T> success(T data) {
        return new HjkResult(MessageData.SUCCESS, data);
    }

    // 用于分页的返回结果
    public static <T, V> HjkResult<T> success(List<V> data, int total) {
        return new HjkResult(MessageData.SUCCESS, new PageResult(data, total));
    }

    public static <T> HjkResult<T> success() {
        return new HjkResult(MessageData.SUCCESS);
    }

    public static HjkResult fail(MessageData messageData) {
        return new HjkResult(messageData);
    }

    public static HjkResult fail() {
        return new HjkResult(MessageData.FAIL);
    }

    public static HjkResult fail(String message) {
        return new HjkResult(1, message, null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReportMessage{" + "code=" + code + ", message='" + message + '\'' +
                ", data=" + data + '}';
    }
}
