package net.hyjuki.smgen.base.common;

public class PageResult {
    private Object data;
    private long total;

    public PageResult(Object data, long total) {
        this.data = data;
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotal() {
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
