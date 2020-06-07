package net.hyjuki.smgen.db;

public class PrimaryKey {
    private String columnName;
    private int dateType;
    private Boolean isAutoIncrement;
    private int keySeq;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public int getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(int keySeq) {
        this.keySeq = keySeq;
    }

    @Override
    public String toString() {
        return "PrimaryKey{" +
                "columnName='" + columnName + '\'' +
                ", dateType=" + dateType +
                ", isAutoIncrement=" + isAutoIncrement +
                ", keySeq=" + keySeq +
                '}';
    }
}
