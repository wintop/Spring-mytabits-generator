package net.hyjuki.smgen.base.utils;

public class Pageable {
    private int pageNo;
    private int pageSize;
    private byte sort;

    public Pageable() {
    }

    public Pageable(int pageNo, int pageSize, byte sort) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public static Pageable of(int pageNo, int pageSize) {
        return new Pageable(pageNo, pageSize, (byte) 0);
    }

    public static Pageable of(int pageNo, int pageSize, byte total) {
        return new Pageable(pageNo, pageSize, total);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public byte getSort() {
        return sort;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }
}
