package net.hyjuki.smgen.db;

import java.util.Arrays;
import java.util.List;

public class Table {
    private String tableCat;
    private String tableName;
    private String remarks;
    private PrimaryKey primaryKey;
    private List<TableColumn> columns;

    public Table(String tableName, String tableCat, String remarks) {
        this.tableName = tableName;
        this.tableCat = tableCat;
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

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKeys(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableCat='" + tableCat + '\'' +
                ", tableName='" + tableName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", primaryKey=" + primaryKey +
                ", columns=" + Arrays.toString(columns.toArray()) +
                '}';
    }
}
