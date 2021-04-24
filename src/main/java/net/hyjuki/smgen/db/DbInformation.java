package net.hyjuki.smgen.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbInformation {
    // 获取表和字段信息时，需要的标签
    private String TABLE = "TABLE";
    private String TABLE_NAME = "TABLE_NAME";
    private String REMARKS = "REMARKS";
    private String TABLE_CAT = "TABLE_CAT";
    private String COLUMN_NAME = "COLUMN_NAME";
    private String KEY_SEQ = "KEY_SEQ";
    private String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";
    private String DATA_TYPE = "DATA_TYPE";
    private String TYPE_NAME = "TYPE_NAME";

    private String driver;
    private String url;
    private String user;
    private String password;
    private DatabaseMetaData dbMetaData;
    private Connection connection;
    private String tableCat;
    private String tableName = null;
    private String schemaPattern = null;
    private String[] type = new String[]{TABLE};
    private List<Table> tables = new ArrayList<>();
    private String columnName;

    public DbInformation(String driver, String url,
                         String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public DbInformation(String driver, String url,
                         String user, String password,
                         String tableCat) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.tableCat = tableCat;
    }

    public void set(String tableCat, String schemaPattern,
                    String[] type, String tableName) {
        this.tableCat = tableCat;
        this.schemaPattern = schemaPattern;
        this.type = type;
        this.tableName = tableName;
    }

    public Connection getConnection() {
        try {
            Properties props = new Properties();
            Class.forName(driver);
            props.setProperty("user", user);
            props.setProperty("password", password);
            // 必须设置才能获取 REMARKS 的信息，否则 REMARKS 为空
            props.setProperty("remarks", "true"); //设置可以获取remarks信息
            props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            connection = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private DatabaseMetaData getDbMetaData() throws SQLException {
        dbMetaData = connection.getMetaData();
        return dbMetaData;
    }

    private ResultSet getTableResultSet() throws SQLException {
        return connection.getMetaData().getTables(tableCat, schemaPattern, tableName, type);
    }

    public ResultSet getTableResultSet(String tableName) throws SQLException {
        return connection.getMetaData().getTables(tableCat, schemaPattern, tableName, type);
    }

    private ResultSet getColumnResultSet(String tableName) throws SQLException {
        return connection.getMetaData().getColumns(tableCat, schemaPattern, tableName, columnName);
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<Table> getTables() throws SQLException {
        ResultSet rsTable = getTableResultSet();
        while (rsTable.next()) {
            String tblName = rsTable.getString(TABLE_NAME);
            String tableCat = rsTable.getString(TABLE_CAT);
            String remarks = rsTable.getString(REMARKS);
            tables.add(getTableFromSet(tblName, tableCat, remarks));
        }

        return tables;
    }

    public Table getTable(String tableName) throws SQLException {
        ResultSet rsTable = getTableResultSet();
        while (rsTable.next()) {
            if (tableName.equals(rsTable.getString(TABLE_NAME))){
                String tableCat = rsTable.getString(TABLE_CAT);
                String remarks = rsTable.getString(REMARKS);
                return getTableFromSet(tableName, tableCat, remarks);
            }
        }

        return null;
    }

    public Table getTableFromSet(String tableName, String tableCat, String remarks) throws SQLException {
        Table table = new Table(tableName, tableCat, remarks);

        List<PrimaryKey> keys = new ArrayList<>();
        List<Column> columns = getColumnsFromDb(tableName);

        // 设置每张表的Primary Key信息
        ResultSet rsKey = connection.getMetaData().getPrimaryKeys(tableCat, null, tableName);

        while (rsKey.next()) {
            PrimaryKey key = new PrimaryKey();
            String columnName = rsKey.getString(COLUMN_NAME);
            key.setColumnName(columnName);
            key.setKeySeq(rsKey.getInt(KEY_SEQ));

            for (Column column: columns) {
                if (columnName.equals(column.getColumnName())) {
                    key.setDateType(column.getDataType());
                    key.setAutoIncrement(column.getIsAutoIncrement());
                }
            }

            keys.add(key);
        }

        table.setPrimaryKeys(keys);
        table.setColumns(columns);
        return table;
    }

    public List<Column> getColumnsFromDb(String tableName) {
        List<Column> columns = new ArrayList<>();
        try {
            ResultSet columnnSet = getColumnResultSet(tableName);
            while (columnnSet.next()) {
                String autoincrement = columnnSet.getString(IS_AUTOINCREMENT);
                Boolean isAuto = false;
                if ("YES".equals(autoincrement.trim())) {
                    isAuto = true;
                }
                Column column = new Column(columnnSet.getString(COLUMN_NAME),
                        columnnSet.getInt(DATA_TYPE), columnnSet.getString(TYPE_NAME),
                        columnnSet.getString(REMARKS), isAuto);
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }
}
