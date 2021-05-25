package net.hyjuki.smgen.base.common;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    SUBMIT(10, "提交"),

    NO_PAY(11, "待付款"),
    PAY_FAILURE(12, "付款失败"),
    PAY_PART(13, "部分付款"),
    ORDER_PAY(14, "已付款"),

    ORDER_FINISH(62, "完成"),
    // 以上是顾客完成状态

    CUST_COMMENT(71, "客户评价"),

    ;

    private byte code;
    private String status;

    OrderStatus(int code, String status) {
        this.code = (byte) code;
        this.status = status;
    }

    public byte code() {
        return code;
    }

    public String status() {
        return status;
    }

    public static String status(Byte code) {
        for (OrderStatus status: OrderStatus.values()) {
            if (status.code == code.byteValue()) {
                return status.status;
            }
        }
        return null;
    }

    public static Map<Integer, String> getStatus() {
        OrderStatus[] statuses = OrderStatus.values();
        Map<Integer, String> map = new HashMap<>();
        for (OrderStatus status: statuses) {
            map.put(Integer.valueOf(status.code()), status.status());
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getStatus());
    }
}
