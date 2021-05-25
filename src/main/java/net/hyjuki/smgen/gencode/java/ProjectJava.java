package net.hyjuki.smgen.gencode.java;

import net.hyjuki.smgen.base.BaseProject;
import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.FileUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;
import net.hyjuki.smgen.db.*;
import net.hyjuki.smgen.gencode.html.HtmlClass;
import net.hyjuki.smgen.gencode.java.base.Annotation;
import net.hyjuki.smgen.gencode.java.base.AnnotationEnum;
import net.hyjuki.smgen.gencode.java.base.JavaClass;
import net.hyjuki.smgen.gencode.java.base.TypeClass;
import net.hyjuki.smgen.gencode.xml.MapperXml;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.model.TableColumn;

import java.util.ArrayList;
import java.util.List;

public class ProjectJava extends BaseProject {
    // net.hyjuki.szmenu
    private String pkgName;
    // E:\mysrc\java\szmenu
    private String projectDir;
    // szmenu
    private String projectName;

    private BaseInterface baseDao;
    private BaseInterface baseService;
    private GenericService genericService;
    private List<ModelClass> models;
    private List<BaseInterface> daos;
    private List<BaseInterface> services;
    private List<ServiceImpl> serviceImpls;
    private List<ControllerClass> constrollers;

    private HtmlClass htmlClass;

    public ProjectJava(String projectName, String projectDir, String pkgName) {
        this.projectName = projectName;
        this.projectDir = projectDir;
        this.pkgName = pkgName;
        this.init(pkgName);

        this.htmlClass = new HtmlClass();
    }

    public String getPkgName() {
        return pkgName;
    }

    /**
     * 生成BaseDao接口，用于生成Dao接口
     * @param baseDaoName
     */
    public void generatorBaseDao(String baseDaoName) {
        this.baseDao = new BaseInterface(baseDaoName, this.getBaseDaoPkg());
        this.baseDao.genBaseInterface();
    }

    /**
     * 生成BaseService接口，用于生成Service接口
     * @param baseServiceName
     */
    public void generatorBaseService(String baseServiceName) {
        this.baseService = new BaseInterface(baseServiceName, this.getBaseSevicePkg());
        this.baseService.genBaseInterface();
    }

    /**
     * 生成GenericService类，用于生成ServiceImpl类
     * @param genericServiceName
     */
    public void generatorGenericService(String genericServiceName) {
        this.genericService = new GenericService(genericServiceName, this.getBaseSevicePkg());

        // BaseService如果用this.baseService.getTypeClass() 模板部分会出现覆盖现象
        TypeClass baseServiceType = new TypeClass(this.baseService.getName(),
                GenUtils.concatPackage(this.baseService.getPkgName().getName(), this.baseService.getName()),
                JavaClass.TEMPLATE.getTypeClass());
        this.genericService.setImplement(baseServiceType);
        this.genericService.setBody(this.baseDao.getName(),
                GenUtils.concatPackage(this.getBaseDaoPkg(), this.baseDao.getName()));
    }

    /**
     * 生成默认的Base方法
     */
    public void generatorBaseClass() {
        String baseDaoName = JavaConstants.NAME_BASE_DAO;
        String baseServiceName = JavaConstants.NAME_BASE_SERVICE;
        String genericServiceName = JavaConstants.NAME_GENERIC_SERVICE;

        this.generatorBaseDao(baseDaoName);
        if (FileUtils.writeFile(getJavaFileName(this.baseDao.getPkgName().getName(), baseDaoName),
                this.baseDao.formatString())) {
            System.out.println("BaseDao接口文件生成成功。");
        }
        else {
            System.out.println("BaseDao接口文件生成失败！！！！！！！！");
        }

        this.generatorBaseService(baseServiceName);
        if (FileUtils.writeFile(getJavaFileName(this.baseService.getPkgName().getName(), baseServiceName),
                this.baseService.formatString())) {
            System.out.println("BaseService接口文件生成成功。");
        }
        else {
            System.out.println("BaseService接口文件生成失败！！！！！！！！");
        }
        this.generatorGenericService(genericServiceName);
        if (FileUtils.writeFile(getJavaFileName(this.genericService.getPkgName().getName(), genericServiceName),
                this.genericService.formatString())) {
            System.out.println("GenericService接口文件生成成功。");
        }
        else {
            System.out.println("GenericService接口文件生成失败！！！！！！！！");
        }
    }

    public TypeClass generatorModel(Table table) {
        String modelName = GenUtils.getClassName(table.getTableName());
        ModelClass model = new ModelClass(modelName, this.getModelPkg());
        model.setComment(table.getRemarks());

        List<TableColumn> columns = table.getColumns();

        for (TableColumn column: columns) {
            model.addElement(column.getName(), TypeClass.getTypeClass(
                    DbDataType.getInstance().getName(column.getDataType())), column.getComment());
        }
        model.genToStringMehtod();

        if (this.models == null) {
            this.models = new ArrayList<ModelClass>();
        }
        this.models.add(model);
        FileUtils.writeFile(getJavaFileName(this.getModelPkg(), modelName), model.formatString());

        return model.getTypeClass();
    }

    public TypeClass generatorDao(String daoName, TypeClass baseDao) {
        BaseInterface dao = new BaseInterface(daoName, this.getDaoPkg());
        dao.addAnnotation(new Annotation(AnnotationEnum.MAPPER));
        dao.genInterface(baseDao);

        if (this.daos == null) {
            this.daos = new ArrayList<BaseInterface>();
        }
        this.daos.add(dao);

        FileUtils.writeFile(getJavaFileName(this.getDaoPkg(), daoName),
                dao.formatString());

        return dao.getTypeClass();
    }

    public TypeClass generatorService(String serviceName, TypeClass baseService) {
        BaseInterface service = new BaseInterface(serviceName, this.getServicePkg());

        service.genInterface(baseService);

        if (this.services == null) {
            this.services = new ArrayList<BaseInterface>();
        }

        this.services.add(service);

        FileUtils.writeFile(getJavaFileName(service.getPkgName().getName(), serviceName),
            service.formatString());

        return service.getTypeClass();
}

    public void generatorServiceImpl(String modelName, TypeClass extend,
                                     TypeClass implement, TypeClass propertyType) {
        ServiceImpl impl = new ServiceImpl(modelName, extend, implement, this.getImplPkg());
        TypeClass baseDaoType = this.baseDao.getTypeClass();
        impl.genClass(new TypeClass(baseDaoType.getSimpleName(), baseDaoType.getPkgName()), propertyType);

        if (this.serviceImpls == null) {
            this.serviceImpls = new ArrayList<ServiceImpl>();
        }
        this.serviceImpls.add(impl);
        FileUtils.writeFile(getJavaFileName(this.getImplPkg(), impl.getName()), impl.formatString());
    }

    public void generatorController(Table table, TypeClass service, String pkgName) {
        ControllerClass controller = new ControllerClass(table, service, pkgName);
        controller.genClass();
        if (this.constrollers == null) {
            this.constrollers = new ArrayList<ControllerClass>();
        }
        this.constrollers.add(controller);
        FileUtils.writeFile(getJavaFileName(this.getCtrlPkg(), controller.getName()), controller.formatString());
    }

    private String getJavaFileName(String pkgName, String name) {
        return GenUtils.concatDir(projectDir, projectName,
                GenUtils.DIR_SRC, GenUtils.DIR_MAIN,
                JavaConstants.DIR_JAVA,
                GenUtils.pkgToDir(pkgName),
                name + GenUtils.EXT_NAME_JAVA);
    }

    /**
     * 根据表信息生成和表名对应的Dao，Service，ServiceImpl文件
     * @param table
     */
    public String generatorByTable(Table table) throws Exception {
        // 生成各个类的名字
        String modelName = GenUtils.getClassName(table.getTableName());
        String daoName = modelName + JavaConstants.NAME_DAO;
        String serviceName = modelName + JavaConstants.NAME_SERVICE;
        // BaseDao类的类信息
        if (this.baseDao == null) {
            generatorBaseDao(JavaConstants.NAME_BASE_DAO);
        }
        TypeClass baseDaoType = this.baseDao.getTypeClass();
        // BaseService类的类信息
        if (this.baseService == null) {
            generatorBaseService(JavaConstants.NAME_BASE_SERVICE);
        }
        TypeClass baseServiceType = this.baseService.getTypeClass();

        // 生成model类
        TypeClass modelType = this.generatorModel(table);
        // 为BaseDao增加模板类
        baseDaoType.setTemplate(modelType);

        // 生成Dao的接口类
        TypeClass daoType = this.generatorDao(daoName, baseDaoType);
        // 生成service接口，并获得service接口的信息
        baseServiceType.setTemplate(modelType);
        TypeClass service = this.generatorService(serviceName, baseServiceType);

        if (this.genericService == null) {
            generatorGenericService(JavaConstants.NAME_GENERIC_SERVICE);
        }
        TypeClass genericService = this.genericService.getTypeClass();
        genericService.setTemplate(modelType);
        this.generatorServiceImpl(modelName, genericService, service, daoType);

        this.generatorController(table, service, this.pkgName);

        String fileName = GenUtils.lowerCase(modelName);
        this.htmlClass.generatorVueFile(table.getTableName(), table.getColumns(),
                GenUtils.concatDir(projectDir, projectName, GenUtils.DIR_WEB, GenUtils.DIR_VIEWS,
                        fileName, "index.vue"));
        this.htmlClass.generatorJsFile(table.getTableName(),
                GenUtils.concatDir(projectDir, projectName, GenUtils.DIR_WEB, GenUtils.DIR_API,
                        GenUtils.concatByDot(fileName, "js")));

        MapperXml mapperXml = new MapperXml(pkgName, table);
        mapperXml.setElements();
        String xmlFile = GenUtils.concatDir(projectDir, projectName,
                GenUtils.DIR_SRC, GenUtils.DIR_MAIN,
                MapperConstants.DIR_RESOURCES,
                MapperConstants.DIR_MAPPER,
                mapperXml.getClassName()+ GenUtils.EXT_NAME_XML);

        if (FileUtils.writeFile(xmlFile, mapperXml.formatString())) {
            System.out.println("==MapperXml file [" + xmlFile + "] is created!");
        }

        return table.getTableName();
    }
}
