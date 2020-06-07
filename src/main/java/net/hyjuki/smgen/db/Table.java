package net.hyjuki.smgen.db;

import java.util.List;

public class Table {
    private String tableCat;
    private String tableName;
    private String remarks;
    private List<PrimaryKey> primaryKeys;
    private List<Column> columns;

    public Table(String tableCat, String tableName, String remarks) {
        this.tableCat = tableCat;
        this.tableName = tableName;
        this.remarks = remarks;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableCat='" + tableCat + '\'' +
                ", tableName='" + tableName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", primaryKeys=" + primaryKeys +
                ", columns=" + columns +
                '}';
    }
}
