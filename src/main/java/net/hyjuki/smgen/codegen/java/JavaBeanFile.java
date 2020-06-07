package net.hyjuki.smgen.codegen.java;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.java.base.*;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.db.Table;

import java.sql.Types;
import java.util.Date;
import java.util.List;

public class JavaBeanFile extends ClassFile {
    private List<Column> columns;
    private boolean tsMark;
    private int tsMaxLength = 78;
    private int tsLength = 78;

    public JavaBeanFile(String packageName, Table table) {
        super(packageName, table);
        this.columns = table.getColumns();
    }


    public Property setProperty(String type, String property, String comment) {
        Property clsProperty = new ClassProperty();
        if (CommonUtils.strIsEmpty(comment)) {
            clsProperty.setComment(comment);
        }
        clsProperty.setModifier(JavaConstants.PRIVATE);
        clsProperty.setType(type);
        clsProperty.setProperty(property);

        return clsProperty;
    }

    public Method setGetMethod(String type, String property) {
        Method method = new DefineMethod();
        method.setModifier(JavaConstants.PUBLIC);
        method.setType(type);
        method.setMethod(JavaConstants.NAME_GET + CommonUtils.upperCase(property));
        method.setBody(JavaConstants.RETURN + " " + property);

        return method;
    }

    public Method setSetMethod(String type, String property) {
        Method method = new DefineMethod();
        method.setModifier(JavaConstants.PUBLIC);
        method.setType(JavaConstants.VOID);
        method.setMethod(JavaConstants.NAME_SET + CommonUtils.upperCase(property));
        method.addParameter(new Parameter(type, property));
        method.setBody(JavaConstants.THIS + "." + property
                + " = " + property);

        return method;
    }

    public Method setToStringMethod(String body) {
        Method toString = new DefineMethod();
        toString.addAnnotation(JavaConstants.ANNOTATION_OVERRIDE);
        toString.setModifier(JavaConstants.PUBLIC);
        toString.setType(String.class.getSimpleName());
        toString.setMethod("toString");
        StringBuilder sb = new StringBuilder();
        sb.append(JavaConstants.RETURN).append(" ")
                .append("\"").append(className)
                .append(" [");
        tsMark = false;
        sb.append(body);
        sb.append("+ \"]\"");
        toString.setBody(sb.toString());

        return toString;
    }

    private void renderToString(StringBuilder sb, String property) {
        // ", property: " + property |+ ", property: " + property
        // , property1: propertyValue1|, property2: propertyValue2
        if (sb.length() + property.length() > tsLength) {
            sb.append(CommonUtils.lineAndIndent(4));
            tsLength = sb.length() + property.length() + tsMaxLength;
        }
        if (tsMark) {
            sb.append("+ \", ");
        }
        tsMark = true;
        sb.append(property).append(": \" ");
        if (sb.length() + property.length() > tsLength) {
            sb.append(CommonUtils.lineAndIndent(4));
            tsLength = sb.length() + property.length() + tsMaxLength;
        }

        sb.append("+ ").append(property).append(" ");
    }

    public void generatorJavaBean() {
        // 用于生成toString()方法
        StringBuilder sb = new StringBuilder();
        // 为每个属性换行
        this.setSeperator(CommonUtils.lineAndIndent(1));
        // 包名
        this.setPackage(packageName);
        // 完整默认的包名
        this.setClassPackage(JavaConstants.DIR_MODEL);
        // 类名 不用继承
        this.setModifier(JavaConstants.PUBLIC);
        this.setType(JavaConstants.CLASS);
        this.setClassName(className);

        for (Column column: columns) {
            int type = column.getDataType();
            String property = CommonUtils.getProperty(column.getColumnName());
            String comment = column.getRemarks();

            if (type == Types.TIMESTAMP
                    || column.getDataType() == Types.DATE
                    || column.getDataType() == Types.TIME) {
                // 引入包
                if (!this.contains(Date.class.getName())) {
                    this.addImportPackage(Date.class.getName());
                }
            }

            String typeName = dataType.getName(type);
            // add properties
            this.addProperty(setProperty(typeName, property, comment));
            // add methods getXxx()
            this.addMethod(setGetMethod(typeName, property));
            // add mothods setXxx()
            this.addMethod(setSetMethod(typeName, property));
            this.renderToString(sb, property);
        }

        // add method toString()
        this.addMethod(setToStringMethod(sb.toString()));
    }
}
