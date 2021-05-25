package net.hyjuki.smgen.db;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.model.TableColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private String tableCat;
    private String tableName;
    private String remarks;
    private List<PrimaryKey> primaryKeys;
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

    public List<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKey(List<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public void setPrimaryKeys(PrimaryKey... primaryKeys) {
        if(GenUtils.isEmpty(this.primaryKeys)) {
            this.primaryKeys = new ArrayList<>();
        }
        if (primaryKeys != null) {
            for (PrimaryKey primaryKey: primaryKeys) {
                this.primaryKeys.add(primaryKey);
            }
        }
    }

    public void addPrimaryKey(PrimaryKey primaryKey) {
        if(GenUtils.isEmpty(this.primaryKeys)) {
            this.primaryKeys = new ArrayList<>();
        }
        this.primaryKeys.add(primaryKey);
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
                ", primaryKeys=" + Arrays.toString(primaryKeys.toArray()) +
                ", columns=" + Arrays.toString(columns.toArray()) +
                '}';
    }
}
