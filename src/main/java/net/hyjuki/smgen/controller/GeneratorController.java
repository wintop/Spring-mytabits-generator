package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.codegen.MyBatisGenerator;
import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.model.DbConnConfig;
import net.hyjuki.smgen.model.FileDirConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GeneratorController {
    private FileDirConfig fileDirConfig;
    private DbInformation dbInformation;
    @RequestMapping("/setConfig")
    public List<Table> getTables(String url, String user, String password, String tableCat,
                      String rootDir, String packageName, String[] fileDir, String[] methodName,
                      String xmlDir, String modelDir, String daoDir, String serviceDir, String implDir,
                      String getName, String findName, String saveName, String updateName, String removeName)
            throws SQLException {
        DbConnConfig dbConnConfig = new DbConnConfig();
        dbConnConfig.setDriver("com.mysql.cj.jdbc.Driver");
        dbConnConfig.setUrl(url);
        dbConnConfig.setUser(user);
        dbConnConfig.setPassword(password);
        dbConnConfig.setTableCat(tableCat);

        dbInformation = new DbInformation(dbConnConfig.getDriver(), url, user, password, tableCat);
        dbInformation.getConnection();
        // List<Table> tables = dbInformation.getTables();
        fileDirConfig = new FileDirConfig();
        fileDirConfig.setRootDir(rootDir);
        fileDirConfig.setPackageName(packageName);

        fileDirConfig.setFileDirs(fileDir);
        fileDirConfig.setXmlDir(xmlDir);
        fileDirConfig.setModelDir(modelDir);
        fileDirConfig.setDaoDir(daoDir);
        fileDirConfig.setServiceDir(serviceDir);
        fileDirConfig.setImplDir(implDir);

        List<Table> tables = dbInformation.getTables();

        return tables;
    }

    @RequestMapping("generator_file")
    @ResponseBody
    public Map<String, String> generatorFile(String[] tables) {
        Map<String, String> mapResult = new HashMap<>();

        System.out.println(Arrays.toString(tables));
        System.out.println("========fileDirConfig= " + fileDirConfig);
        for (String tableName: tables) {
            try {
                System.out.println("=========== " + tableName);
                Table table = dbInformation.getTable(tableName);
                MyBatisGenerator gen = new MyBatisGenerator(fileDirConfig.getPackageName(), fileDirConfig.getRootDir(),table);
                gen.generatorComboFiles();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        mapResult.put("code", "0");
        mapResult.put("message", "操作成功");
        return mapResult;
    }
    @RequestMapping("generator_config")
    @ResponseBody
    public Map<String, String> generatorFile(String rootDir, String packageName) {
        Map<String, String> mapResult = new HashMap<>();

        try {
            MyBatisGenerator gen = new MyBatisGenerator(packageName, rootDir, null);
            gen.generatorMybatisConfigXmlFile();
            mapResult.put("code", "0");
            mapResult.put("message", "操作成功");
        } catch (IOException e) {
            mapResult.put("code", "1");
            mapResult.put("message", "操作失败");
            e.printStackTrace();
        }

        return mapResult;
    }
}
