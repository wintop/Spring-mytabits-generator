package net.hyjuki.smgen.codegen.java;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.java.base.JavaConstants;

public class InterfaceServiceFile extends InterfaceFile {
    public InterfaceServiceFile(String packageName, Table table) {
        super(packageName, table);
        this.beanPackage = this.setBeanPackage(JavaConstants.DIR_MODEL);
        this.modifier = JavaConstants.PUBLIC;
        this.type = JavaConstants.INTERFACE;
        this.modelName = this.className;
        this.setInterfaceName(JavaConstants.NAME_SERVICE);
        this.setSeperator(CommonUtils.lineAndIndent(1));
    }

    public void generatorInterfaceService() {
        // 完整默认的包名
        this.setClassPackage(JavaConstants.DIR_SERVICE);
        this.setInterfaceName(JavaConstants.NAME_SERVICE);
        this.setImportPackages();
        this.setMethods();;
    }
}
