package net.hyjuki.smgen.db;

import net.hyjuki.smgen.model.TableColumn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbInformation {
    // 获取表和字段信息时，需要的标签
    private static String TABLE = "TABLE";
    private static String TABLE_NAME = "TABLE_NAME";
    private static String REMARKS = "REMARKS";
    private static String TABLE_CAT = "TABLE_CAT";
    private static String COLUMN_NAME = "COLUMN_NAME";
    private static String KEY_SEQ = "KEY_SEQ";
    private static String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";
    private static String DATA_TYPE = "DATA_TYPE";
    private static String TYPE_NAME = "TYPE_NAME";
    private static String NULLABLE = "NULLABLE";

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

    public void SettableCat(String tableCat) {
        this.tableCat = tableCat;
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
        return getConnection().getMetaData().getTables(tableCat, schemaPattern, tableName, type);
    }

    private ResultSet getTableResultSet(String tableCat) throws SQLException {
        return getConnection().getMetaData().getTables(tableCat, schemaPattern, tableName, type);
    }

    public ResultSet getTable(String tableCat, String tableName) throws SQLException {
        return getConnection().getMetaData().getTables(tableCat, schemaPattern, tableName, type);
    }

    private ResultSet getColumnResultSet(String tableName) throws SQLException {
        return this.getConnection().getMetaData().getColumns(tableCat, schemaPattern, tableName, columnName);
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

    public List<Table> getTableList() throws SQLException {
        ResultSet rsTable = getTableResultSet();
        List<Table> tables = new ArrayList<>();
        while (rsTable.next()) {
            String tblName = rsTable.getString(TABLE_NAME);
            String tableCat = rsTable.getString(TABLE_CAT);
            String remarks = rsTable.getString(REMARKS);
            tables.add(new Table(tblName, tableCat, remarks));
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

    public List<String> getCatalogs() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet catalogs = this.getConnection().getMetaData().getCatalogs();
        while (catalogs.next()) {
            list.add(catalogs.getString("TABLE_CAT"));
        }

        return list;
    }

    public Table getTableFromSet(String tableName, String tableCat, String remarks) throws SQLException {
        Table table = new Table(tableName, tableCat, remarks);

        PrimaryKey keys = new PrimaryKey();
        List<TableColumn> columns = getColumns(tableName);

        table.setColumns(columns);
        // 设置每张表的Primary Key信息
        ResultSet rsKey = connection.getMetaData().getPrimaryKeys(tableCat, null, tableName);

        while (rsKey.next()) {
            PrimaryKey key = new PrimaryKey();
            String columnName = rsKey.getString(COLUMN_NAME);
            key.setColumnName(columnName);
            key.setKeySeq(rsKey.getInt(KEY_SEQ));

            for (TableColumn column: columns) {
                // 只允许有一个primary key
                if (columnName.equals(column.getName())) {
                    key.setDateType(column.getDataType());
                    key.setAutoIncrement(column.getAutoIncrement() == 1);
                    table.addPrimaryKey(key);
                    return table;
                }
            }
        }

        return table;
    }

    public List<TableColumn> getColumns(String tableName) {
        List<TableColumn> columns = new ArrayList<>();
        try {
            ResultSet columnnSet = getColumnResultSet(tableName);
            while (columnnSet.next()) {
                String autoincrement = columnnSet.getString(IS_AUTOINCREMENT);
                Byte isAuto = 0;
                if ("YES".equals(autoincrement.trim())) {
                    isAuto = 1;
                }
                System.out.println("TABLE_CAT: " + columnnSet.getString("TABLE_CAT"));
                System.out.println("TABLE_SCHEM: " + columnnSet.getString("TABLE_SCHEM"));
                System.out.println("TABLE_NAME: " + columnnSet.getString("TABLE_NAME"));
                System.out.println("COLUMN_NAME: " + columnnSet.getString("COLUMN_NAME"));
                System.out.println("DATA_TYPE: " + columnnSet.getInt("DATA_TYPE"));
                System.out.println("TYPE_NAME: " + columnnSet.getString("TYPE_NAME"));
                System.out.println("COLUMN_SIZE: " + columnnSet.getInt("COLUMN_SIZE"));
                System.out.println("BUFFER_LENGTH: " + columnnSet.getInt("BUFFER_LENGTH"));
                System.out.println("DECIMAL_DIGITS: " + columnnSet.getInt("DECIMAL_DIGITS"));
                System.out.println("NUM_PREC_RADIX: " + columnnSet.getInt("NUM_PREC_RADIX"));
                System.out.println("NULLABLE: " + columnnSet.getInt("NULLABLE"));

                System.out.println("REMARKS: " + columnnSet.getString("REMARKS"));
                System.out.println("COLUMN_DEF: " + columnnSet.getString("COLUMN_DEF"));
                System.out.println("SQL_DATA_TYPE: " + columnnSet.getInt("SQL_DATA_TYPE"));
                System.out.println("SQL_DATETIME_SUB: " + columnnSet.getInt("SQL_DATETIME_SUB"));
                System.out.println("CHAR_OCTET_LENGTH: " + columnnSet.getInt("CHAR_OCTET_LENGTH"));
                System.out.println("ORDINAL_POSITION: " + columnnSet.getInt("ORDINAL_POSITION"));
                System.out.println("IS_NULLABLE: " + columnnSet.getString("IS_NULLABLE"));


//                System.out.println("SCOPE_CATALOG: " + columnnSet.getString("SCOPE_CATALOG"));
//                System.out.println("SCOPE_SCHEMA: " + columnnSet.getString("SCOPE_SCHEMA"));
//                System.out.println("SCOPE_TABLE: " + columnnSet.getString("SCOPE_TABLE"));
                System.out.println("SOURCE_DATA_TYPE: " + columnnSet.getInt("SOURCE_DATA_TYPE"));
                System.out.println("IS_AUTOINCREMENT: " + columnnSet.getString("IS_AUTOINCREMENT"));

                System.out.println("IS_GENERATEDCOLUMN: " + columnnSet.getString("IS_GENERATEDCOLUMN"));

                System.out.println("-------------------------------------");
                TableColumn column = new TableColumn(columnnSet.getString(COLUMN_NAME),
                        columnnSet.getInt(DATA_TYPE), columnnSet.getString(TYPE_NAME),
                        columnnSet.getInt("COLUMN_SIZE"), (byte)columnnSet.getInt("NULLABLE"),
                        isAuto, columnnSet.getString(REMARKS));
                column.setNullable(Byte.valueOf((byte) columnnSet.getInt(NULLABLE)));
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public static void main(String[] args) throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String drivero = "oracle.jdbc.OracleDriver";
        String url = "jdbc:mysql://localhost:3306/hjk?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8";
        String urlo = "jdbc:oracle:thin:@39.96.21.80:1522:XE";
        String user = "root";
        String usero = "weiai_test";
        String password = "123456";
        String passwordo = "weiai20190625";
        String tabCat = "WEIAI_TEST";
//        DbInformation dbInformation = new DbInformation(driver, url, user, password, tabCat);
        DbInformation dbInformation = new DbInformation(drivero, urlo, usero, passwordo, tabCat);
        Connection connection = dbInformation.getConnection();
        Table t2 = dbInformation.getTable("t2");
        System.out.println(t2);
        ResultSet catalogs = dbInformation.getConnection().getMetaData().getCatalogs();
    }
}
