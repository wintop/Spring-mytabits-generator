package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.MyBatisGenerator;
import net.hyjuki.smgen.codegen.xml.MybatisConfigXml;
import net.hyjuki.smgen.codegen.xml.base.Attribute;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.db.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemoTest {
    public static List<Attribute> list = new ArrayList<>();
    public static String str = "123";
    public static void main(String[] args) throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/my_test?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        String tabCat = "szmap";
        String tableName = "Area";
        String packageName = "net.hyjuki.demo";
        String rootDir = "E:\\mysrc\\java\\mytest123";

        DbInformation dbInformation = new DbInformation(driver, url, user, password, tabCat);
        dbInformation.getConnection();
        //    List<Table> tables = dbInformation.getTables();

        Table table = dbInformation.getTable(tableName);
        tableName = table.getTableName();
        String resultMap = CommonUtils.getClassName(tableName) + MapperConstants.MAP;
        List<PrimaryKey> keys = table.getPrimaryKeys();
        List<Column> columns = table.getColumns();

        DbDataType dataType = DbDataType.getInstance();

        MyBatisGenerator gen = new MyBatisGenerator(packageName, rootDir, table);
        try {
            gen.generatorMybatisConfigXmlFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------");
        gen.setProjectDir();
        System.out.println(gen.getProjectDir());

        MybatisConfigXml configXml = new MybatisConfigXml(packageName);
        System.out.println(configXml.formatString());
    }
}
