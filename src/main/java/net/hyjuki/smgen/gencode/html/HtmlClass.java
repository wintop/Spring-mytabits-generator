package net.hyjuki.smgen.gencode.html;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.model.TableColumn;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlClass {
    private Configuration cfg;
    private static final String jsTemplate = "api.js.ftl";
    private static final String vueTemplate = "model.vue.ftl";

    public HtmlClass() {
        try {
            File templateFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates");

            cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setDefaultEncoding("UTF-8");
            FileTemplateLoader ftl = new FileTemplateLoader(templateFile);
            cfg.setTemplateLoader(ftl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatorJsFile(String tableName, String destFile) throws IOException, TemplateException {
        Map<String, Object> dataModel = new HashMap<>();
        String className = GenUtils.getClassName(tableName);
        String urlName = tableName;
        dataModel.put("className", className);
        dataModel.put("urlName", urlName);
        generatorWebFile(dataModel, jsTemplate, destFile);
    }
    public void generatorVueFile(String tableName, List<TableColumn> columns, String destFile)
            throws IOException, TemplateException {
        Map<String, Object> dataModel = new HashMap<>();
        String className = GenUtils.getClassName(tableName);
        dataModel.put("className", className);
        dataModel.put("objName", GenUtils.lowerCase(className));
        dataModel.put("columns", columns);

        generatorWebFile(dataModel, vueTemplate, destFile);
    }

    private void generatorWebFile(Map<String, Object> dataModel, String templateFile, String destFile)
            throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateFile);

        File file = new File(destFile);
        if (!file.getParentFile().exists()) {
            FileUtils.forceMkdir(file.getParentFile().getAbsoluteFile());
        }

        FileWriter fileWriter = new FileWriter(file);
        template.process(dataModel, fileWriter);
        fileWriter.close();
        System.out.println("--Vue File [" + file.getAbsolutePath());
    }
}
