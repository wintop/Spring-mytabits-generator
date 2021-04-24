package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.base.JavaConstants;

public class Import implements Comparable<Import> {
    String value;

    public Import(String value) {
        this.value = value;
    }

    public Import(String packageName, String className) {
        this.value = CommonUtils.concatPackage(packageName, className);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(String packageName, String className) {
        this.value = CommonUtils.concatPackage(packageName, className);
    }

    public String formatString() {
        return JavaConstants.IMPORT + " " + this.value + ";";
    }

    /**
     * Import需要排序，重写compareTo方法
     * @param impt 比较对象
     * @return
     */
    @Override
    public int compareTo(Import impt) {
        return this.value.compareTo(impt.value);
    }
}
