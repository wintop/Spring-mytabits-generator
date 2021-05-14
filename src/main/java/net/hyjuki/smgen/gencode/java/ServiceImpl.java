package net.hyjuki.smgen.gencode.java;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;
import net.hyjuki.smgen.gencode.java.base.*;

public class ServiceImpl extends BaseClass {
    private ClassTemplate serviceImpl;
    private String genericName = JavaConstants.NAME_GENERIC_SERVICE;
    private String modelName;
    private String daoName;
    private String serviceName;
    private String implName;

    ServiceImpl(String name, TypeClass extend, TypeClass implement, String pkgName) {
        super(ClassType.CLASS, name + JavaConstants.NAME_SERVICE_IMPL, pkgName);
        this.modelName = name;
        this.daoName = GenUtils.upperCase(modelName) + JavaConstants.NAME_DAO;
        this.serviceName = modelName + JavaConstants.NAME_SERVICE;
        this.implName = modelName + JavaConstants.NAME_SERVICE_IMPL;
        serviceImpl = new ClassTemplate(Modifier.PUBLIC, ClassType.CLASS,
                implName, "",  extend, implement);
    }

    ServiceImpl(String name, String pkgName) {
        super(ClassType.CLASS, GenUtils.getClassName(name), pkgName);
        modelName = name;
        this.daoName = GenUtils.upperCase(modelName) + JavaConstants.NAME_DAO;
        this.serviceName = modelName + JavaConstants.NAME_SERVICE;
        this.implName = modelName + JavaConstants.NAME_SERVICE_IMPL;
        this.setPkgName(pkgName);

        TypeClass template = new TypeClass(modelName, GenUtils.concatPackage(pkgName, modelName));
        TypeClass extend = new TypeClass(genericName, GenUtils.concatPackage(pkgName, genericName), template);
        TypeClass implement = new TypeClass(serviceName, GenUtils.concatPackage(pkgName, serviceName));

        serviceImpl = new ClassTemplate(Modifier.PUBLIC, ClassType.CLASS,
                implName, "", extend, implement);
    }


    public void genClass(TypeClass baseDao, TypeClass propertyType) {
        serviceImpl.addAnnotation(new Annotation(AnnotationEnum.SERVICE));
        String propertyName = GenUtils.lowerCase(modelName) + JavaConstants.NAME_DAO;

        Property property = new Property(propertyType, propertyName);
        property.addAnnotation(new Annotation(AnnotationEnum.AUTOWIRED));

        ClassBody body = new ClassBody();
        body.addProperty(property);

        String pkgName = this.getPkgName().getName();
        ClassMethod method = new ClassMethod(Modifier.PUBLIC, baseDao,
                    GenUtils.genGetMethodName(baseDao.getClassName()),
                null);
        method.addAnnotations(new Annotation(AnnotationEnum.OVERRIDE));
        MethodBody methodBody = new MethodBody(new ClassReturn(propertyName));
        method.setBody(methodBody);
        body.addMethod(method);

        serviceImpl.setBody(body);
        serviceImpl.setPkgName(pkgName);
    }

    public String formatString() {
        return serviceImpl.formatString();
    }

    public static void main(String[] args) {
        TypeClass extend = new TypeClass("GenericService", "net.hyjuki.demo.base.service.GenericService");
        TypeClass template = new TypeClass("Administrator", "net.hyjuki.demo.model.Administrator");
        extend.setTemplate(template);
        TypeClass implement = new TypeClass("BaseService", "net.hyjuki.demo.base.BaseService");
        ServiceImpl impl = new ServiceImpl("Administrator", extend, implement, "net.hyjuki.demo.service.impl");

        impl.genClass(new TypeClass("BaseDao", "net.hyjuki.demo.base.BaseDao"),
                new TypeClass("AdministratorDao", "net.hyjuki.demo.dao.AdministratorDao"));
        System.out.println(impl.formatString());
    }
}
