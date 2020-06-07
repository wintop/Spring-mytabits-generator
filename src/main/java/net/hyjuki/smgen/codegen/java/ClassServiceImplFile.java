package net.hyjuki.smgen.codegen.java;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.MethodConfig;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.java.base.*;

import java.util.List;

public class ClassServiceImplFile extends InterfaceServiceFile {
    private String serviceName;
    private String daoName;
    private String packageService;
    private String daoPackage;

    public ClassServiceImplFile(String packageName, Table table) {
        super(packageName, table);
        // public class XxxServiceImpl implements XxxService =======
        this.modifier = JavaConstants.PUBLIC;
        this.type = JavaConstants.CLASS;
        // ServiceImpl className
        this.className = this.modelName + JavaConstants.NAME_SERVICE_IMPL;
        // ServiceName
        this.serviceName = this.modelName + JavaConstants.NAME_SERVICE;
        this.addParentClass(new ParentClass(JavaConstants.IMPLEMENTS, this.serviceName));
        // public class XxxServiceImpl implements XxxService ========

        // MapperName
        this.daoName = this.modelName + JavaConstants.NAME_DAO;
        // import package;
        this.packageService = CommonUtils.concatPackage(this.packageName,
                JavaConstants.DIR_SERVICE, this.serviceName);
        //  可配置
        this.beanPackage = this.setBeanPackage(JavaConstants.DIR_MODEL);
        //  可配置
        this.daoPackage = CommonUtils.concatPackage(packageName,
                JavaConstants.DIR_DAO, this.daoName);

        this.setSeperator(CommonUtils.lineAndIndent(1));
    }

    @Override
    public void setImportPackages() {
        this.addImportPackage(JavaConstants.PACKAGE_ANNOTATION_AUTOWIRED);
        this.addImportPackage(JavaConstants.PACKAGE_ANNOTATION_SERVICE);
        this.addImportPackage(this.packageService);
        this.addImportPackage(this.daoPackage);
        // JavaBean package
        this.addImportPackage(beanPackage);
        // import List package
        this.addImportPackage(List.class.getName());
    }

    /**
     * impl类上需要加@Service注解，同时引入包名
     */
    public void setAnnotation() {
        this.addAnnotation(JavaConstants.ANNOTATION_SERVICE);
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public void setDaoName(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public void setPropertys() {
        Property property = new ClassProperty();
        property.addAnnotation(JavaConstants.ANNOTATION_AUTOWIRED);
        property.setModifier(JavaConstants.PRIVATE);
        property.setType(this.daoName);
        property.setProperty(CommonUtils.lowerCase(this.daoName));
        this.addProperty(property);
    }

    @Override
    public void setGetMedthod(String methodName) {
        super.setGetMedthod(methodName);
        this.getGetMedthod().setModifier(JavaConstants.PUBLIC);
        ClassMethod getMethod = new ClassMethod();
        getMethod.setObjectName(CommonUtils.lowerCase(this.daoName));
        getMethod.setMethod(methodName);
        getMethod.addParameters(this.getKeyParameters());
        this.getGetMedthod().addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        this.getGetMedthod().setBody(JavaConstants.RETURN
                + " " + getMethod.formatString());
    }

    @Override
    public void setGetMedthod() {
        this.setGetMedthod(JavaConstants.NAME_GET);
    }

    @Override
    public void setFindMethod(String methodName) {
        super.setFindMethod(methodName);
        this.getFindMethod().setModifier(JavaConstants.PUBLIC);
        ClassMethod findMethod = new ClassMethod();
        findMethod.setObjectName(CommonUtils.lowerCase(this.daoName));
        findMethod.setMethod(methodName);
        findMethod.addParameter(this.getModelParameter());
        this.getFindMethod().addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        this.getFindMethod().setBody(JavaConstants.RETURN
                + " " + findMethod.formatString());
    }

    @Override
    public void setFindMethod() {
        this.setFindMethod(JavaConstants.METHOD_FIND);
    }

    @Override
    public void setSaveMethod(String methodName) {
        super.setSaveMethod(methodName);
        this.getSaveMethod().setModifier(JavaConstants.PUBLIC);
        ClassMethod saveMethod = new ClassMethod();
        saveMethod.setObjectName(CommonUtils.lowerCase(this.daoName));
        saveMethod.setMethod(methodName);
        saveMethod.addParameter(this.getModelParameter());
        this.getSaveMethod().addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        this.getSaveMethod().setBody(JavaConstants.RETURN
                + " " + saveMethod.formatString());
    }

    @Override
    public void setSaveMethod() {
        this.setSaveMethod(JavaConstants.METHOD_SAVE);
    }

    @Override
    public void setUpdateMethod(String methodName) {
        super.setUpdateMethod(methodName);

        this.getUpdateMethod().setModifier(JavaConstants.PUBLIC);
        ClassMethod updateMethod = new ClassMethod();
        updateMethod.setObjectName(CommonUtils.lowerCase(this.daoName));
        updateMethod.setMethod(methodName);

        updateMethod.addParameter(this.getModelParameter());
        this.getUpdateMethod().addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        this.getUpdateMethod().setBody(JavaConstants.RETURN
                + " " + updateMethod.formatString());
    }

    @Override
    public void setUpdateMethod() {
        this.setUpdateMethod(JavaConstants.METHOD_UPDATE);
    }

    @Override
    public void setRemoveMethod(String methodName) {
        super.setRemoveMethod(methodName);
        this.getRemoveMethod().setModifier(JavaConstants.PUBLIC);
        ClassMethod removeMethod = new ClassMethod();
        removeMethod.setObjectName(CommonUtils.lowerCase(this.daoName));
        removeMethod.setMethod(methodName);
        removeMethod.addParameters(this.getKeyParameters());
        this.getRemoveMethod().addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        this.getRemoveMethod().setBody(JavaConstants.RETURN
                + " " + removeMethod.formatString());
    }

    @Override
    public void setRemoveMethod() {
        this.setRemoveMethod(JavaConstants.METHOD_REMOVE);
    }

    @Override
    public void setMethods() {
        super.setMethods();
    }

    @Override
    public void setMethods(MethodConfig methods) {
        super.setMethods(methods);
    }

    public void generatorServiceImpl() {
        // 完整默认的包名
        this.setClassPackage(CommonUtils.concatPackage(JavaConstants.DIR_SERVICE, JavaConstants.DIR_IMPL));
        setPropertys();
        setImportPackages();
        this.addAnnotation(JavaConstants.ANNOTATION_SERVICE);
        setMethods();
    }
}
