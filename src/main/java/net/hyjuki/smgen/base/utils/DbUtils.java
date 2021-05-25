package net.hyjuki.smgen.base.utils;

import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DbUtils {
    private static String MYSQL = "MySQL";
    private static String POSTGRESQL = "PostgreSQL";
    private static String ORACLE = "Oracle";

    private String name;

    private static Map<String, String> dbDriver = new HashMap<>();

    static {
        if (dbDriver.size() == 0) {
            dbDriver.put(MYSQL, "com.mysql.cj.jdbc.Driver");
            dbDriver.put(POSTGRESQL, "");
        }
    }

    public static DbInformation getDbMySQLInfomation(DataBase dataBase) {
        return new DbInformation(dbDriver.get(MYSQL), getUrl(dataBase),
                dataBase.getUserName(), dataBase.getPassword(), dataBase.getName());
    }

    private static String getUrl(DataBase dataBase) {
        StringBuffer url = new StringBuffer();
        url.append("jdbc:mysql://")
                .append(dataBase.getHost())
                .append(":")
                .append(dataBase.getPort())
                .append("/")
                .append(dataBase.getName())
                .append("?serverTimezone=CTT&useUnicode=true&characterEncoding=")
                .append(dataBase.getCharSet());
        return url.toString();
    }

    public static List<String> getDbMySQLCatalogs(DataBase dataBase) throws SQLException {
        DbInformation dbInformation =  new DbInformation(dbDriver.get(MYSQL), getUrl(dataBase),
                dataBase.getUserName(), dataBase.getPassword(), dataBase.getName());

        System.out.println(dataBase);
        ResultSet resultSet = dbInformation.getConnection().getMetaData().getCatalogs();
        List<String> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(resultSet.getString("TABLE_CAT"));
        }
        return list;
    }
}
