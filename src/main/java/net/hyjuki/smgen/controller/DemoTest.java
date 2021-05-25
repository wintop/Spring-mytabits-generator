package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.utils.CommonUtils;
import net.hyjuki.smgen.gencode.xml.base.Attribute;
import net.hyjuki.smgen.db.*;
import java.util.*;

public class DemoTest {
    public static List<Attribute> list = new ArrayList<>();
    public static String str = "123";

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hjk?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        String tabCat = "szmenu";
        String tableName = "administrator";
        String packageName = "net.hyjuki.demo";
        String rootDir = "E:\\mysrc\\java\\mytest123";

        DbInformation dbInformation = new DbInformation(driver, url, user, password, tabCat);
        dbInformation.getConnection();
        List<Table> tables = dbInformation.getTables();

        Map<Object, Table> map = CommonUtils.listToMap(tables, Table.class.getDeclaredMethod("getTableName"));

        for (Object obj: map.keySet()) {
            System.out.println(map.get(obj).getTableName());
        }

        System.out.println(map);
//
//        Table table = dbInformation.getTable(tableName);
//        System.out.println(table);
//        tableName = table.getTableName();
//        String resultMap = CommonUtils.getClassName(tableName) + MapperConstants.MAP;
//        List<PrimaryKey> keys = table.getPrimaryKeys();
//        List<Column> columns = table.getColumns();
//
//        DbDataType dataType = DbDataType.getInstance();

//        MyBatisGenerator gen = new MyBatisGenerator(packageName, rootDir, table);
//        try {
//            gen.generatorMybatisConfigXmlFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println("-------------------------");
//        gen.setProjectDir();
//        System.out.println(gen.getProjectDir());

//        MybatisConfigXml configXml = new MybatisConfigXml(packageName);
//        System.out.println(configXml.formatString());
    }
}
