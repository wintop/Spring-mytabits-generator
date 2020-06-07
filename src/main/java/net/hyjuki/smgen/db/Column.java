package net.hyjuki.smgen.db;

public class Column {
    private String columnName;
    private int dataType;
    private String typeName;
    private String remarks;
    private Boolean isAutoIncrement;

    public Column(String columnName, int dataType, String typeName,
                  String remarks, Boolean isAutoIncrement) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.typeName = typeName;
        this.remarks = remarks;
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(Boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType=" + dataType +
                ", typeName='" + typeName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isAutoIncrement='" + isAutoIncrement + '\'' +
                '}';
    }
}
