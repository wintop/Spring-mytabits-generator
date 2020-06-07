package net.hyjuki.smgen.codegen.java;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.java.base.JavaConstants;

import java.util.List;

public class InterfaceDaoFile extends InterfaceFile {

    public InterfaceDaoFile(String packageName, Table table) {
        super(packageName, table);
        this.beanPackage = this.setBeanPackage(JavaConstants.DIR_MODEL);
        this.modifier = JavaConstants.PUBLIC;
        this.type = JavaConstants.INTERFACE;
        this.setInterfaceName(JavaConstants.NAME_DAO);
        this.setSeperator(CommonUtils.lineAndIndent(1));

        setImportPackages();
    }

    /**
     * 统一引入包名，方便调整顺序，后期可以优化
     */
    public void setImportPackages() {
        // JavaBean package
        this.addImportPackage(beanPackage);
        // @Mapper package
        this.addImportPackage(JavaConstants.PACKAGE_ANNOTATION_MAPPER);
        // import List package
        this.addImportPackage(List.class.getName());
    }

    public void generatorInterfaceMapper() {
        // 完整默认的包名
        this.setClassPackage(JavaConstants.DIR_DAO);
        this.addAnnotation(JavaConstants.ANNOTATION_MAPPER);
        this.setMethods();
    }
}
