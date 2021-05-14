package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.JavaConstants;

/**
 * 类或者接口所在的包
 * package package directory class name;
 */
public class Package {
    private String packageName;

    Package(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return this.packageName;
    }

    public String getPackage() {
        return JavaConstants.PACKAGE_SPC + this.packageName;
    }
}
