package net.hyjuki.smgen.base.utils;

public class PageResult<T> {
    private T data;
    private int total;

    public PageResult(T data, int total) {
        this.data = data;
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
