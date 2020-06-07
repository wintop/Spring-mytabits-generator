package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;

import java.util.ArrayList;
import java.util.List;

public class ClassFile extends AbstractElement implements Class {
    protected String packageName;
    protected String classPackage;
    protected List<String> importPkgs = new ArrayList<>();
    protected Table table;
    protected String modifier;
    protected String type;    // "class" "interface" "abstract class"
    protected String className;
    protected String modelName;
    protected String genericity;  // "<T>" "<T, V>"
    protected List<Property> properties = new ArrayList<>();
    protected List<ParentClass> parentClasses = new ArrayList<>();
    protected List<Method> methods = new ArrayList<>();
    protected List<PrimaryKey> keys;

    public ClassFile(String packageName, Table table) {
        this.packageName = packageName;
        this.modifier = JavaConstants.PUBLIC;
        this.type = JavaConstants.CLASS;
        this.table = table;
        this.modelName = CommonUtils.getClassName(table.getTableName());
        this.className = modelName;
        this.keys = table.getPrimaryKeys();
    }

    @Override
    public void setPackage(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getPackage() {
        return packageName;
    }

    @Override
    public void setClassPackage(String classPackage) {
        this.classPackage = classPackage;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void addImportPackage(String importPkg) {
        /**
         * 以下需要重新整理
         */
        /*
        if ("java".equals(importPkg.substring(0, 4))) {
            this.importPkgs.add(importPkg);
        }
        String rootPkg = "";
        for (String pkg: this.importPkgs) {
            System.out.println("pkg: " + pkg);
        }
        */
        this.importPkgs.add(importPkg);
    }

    @Override
    public void addImportPackage(int index, String importPkg) {
        this.importPkgs.add(index, importPkg);
    }

    @Override
    public boolean contains(String importPkg) {
        return this.importPkgs.contains(importPkg);
    }

    @Override
    public void addImportPackages(List<String> importPkgs) {
        this.importPkgs = importPkgs;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void setGenericity(String genericity) {
        this.genericity = genericity;
    }

    @Override
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    @Override
    public void addProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public void addMethod(Method method) {
        this.methods.add(method);
    }

    @Override
    public void addMothods(List<Method> methods) {
        this.methods = methods;
    }

    @Override
    public void addParentClass(ParentClass parentClass) {
        this.parentClasses.add(parentClass);
    }

    @Override
    public String formatString() {
        String line = CommonUtils.line();
        StringBuilder sb = new StringBuilder();
        // 包名 package
        sb.append(JavaConstants.PACKAGE).append(" ")
                .append(CommonUtils.concatPackage(this.packageName, classPackage))
                .append(";").append(CommonUtils.line(2));

        // import
        String importRoot = "";
        if (this.importPkgs.size() > 0){
            for (String importPkg: this.importPkgs) {
                String root = importPkg.split("\\.")[0];
                if (!"".equals(importRoot) && !importRoot.equals(root)) {
                    sb.append(line);
                }
                sb.append(JavaConstants.IMPORT).append(" ")
                        .append(importPkg).append(";");
                importRoot = root;
                sb.append(line);
            }
            sb.append(line);
        }

        //
        sb.append(this.formatCommentAnnotations(0));

        // class or interface ... name
        sb.append(modifier).append(" ")
                .append(type).append(" ").append(className);
        if (this.parentClasses != null) {
            for (ParentClass parent: parentClasses) {
                sb.append(" ").append(parent.toString());
            }
        }
        sb.append(" {");

        // property list
        if (properties != null && properties.size() > 0) {
            sb.append(this.listFormatString(properties, seperator));
            sb.append(seperator);
        }

        // method list
        sb.append(this.listFormatString(methods, seperator));
        // 强迫症患者，并且兼容window 和 linux
        if (!line.substring(line.length()-1).equals(sb.substring(sb.length()-1))){
            sb.append(line);
        }

        sb.append("}");

        return sb.toString();
    }
}
