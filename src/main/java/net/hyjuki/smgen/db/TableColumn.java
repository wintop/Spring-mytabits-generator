package net.hyjuki.smgen.db;

import net.hyjuki.smgen.base.utils.GenUtils;

public class TableColumn {
    private String columnName;
    private String propertyName;
    private int dataType;
    private String typeName;
    private String remarks;
    private Boolean isAutoIncrement;

    public TableColumn(String columnName, int dataType, String typeName,
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

    public String getPropertyName() {
        return GenUtils.getProperty(columnName);
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = GenUtils.getProperty(columnName);
    }

    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
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

    @Override
    public String toString() {
        return "Column {" +
                " columnName='" + columnName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", dataType=" + dataType +
                ", typeName='" + typeName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isAutoIncrement='" + isAutoIncrement + '\'' +
                '}';
    }
}
