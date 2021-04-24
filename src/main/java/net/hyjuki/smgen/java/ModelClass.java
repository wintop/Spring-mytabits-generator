package net.hyjuki.smgen.java;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.base.JavaConstants;
import net.hyjuki.smgen.java.base.*;

import java.util.ArrayList;
import java.util.List;

/**
 * public class ClassName {
 *     private String name;
 *     private int age;
 *
 *     public String getName() {
 *         return name;
 *     }
 *
 *     public void setName(String name) {
 *         this.name = name;
 *     }
 *
 *     public int getAge() {
 *         return age;
 *     }
 *
 *     public void setAge(int age) {
 *         this.age = age;
 *     }
 * }
 */
public class ModelClass extends BaseClass {
    // ts: ToString
    private StringBuilder ts = new StringBuilder();
    private int tsMaxLength = 78;
    private int tsLength = 78;

    public ModelClass(String name, String pkgName) {
        super(ClassType.CLASS, name, pkgName);
    }

    public void addElement(String name, TypeClass type, String comment) {
        String propertyName = CommonUtils.getProperty(name);

        Property property = new Property(type, propertyName, comment);
        ClassMethod get = new ClassMethod(Modifier.PUBLIC, type,
                CommonUtils.genGetMethodName(propertyName), null);

        List<Parameter> parameters = new ArrayList<Parameter>();
        MethodBody methodBody = new MethodBody(new ClassReturn(propertyName));
        get.setBody(methodBody);

        parameters.add(new Parameter(type, propertyName));
        ClassMethod set = new ClassMethod(Modifier.PUBLIC, JavaClass.VOID.getTypeClass(),
                CommonUtils.genSetMethodName(propertyName), parameters);

        String retSet = CommonUtils.concatByDot(JavaConstants.THIS, propertyName) + " = " + propertyName;
        set.setBody(new MethodBody(retSet));

        if (this.getBody() == null) {
            this.setBody(new ClassBody());
        }

        this.getBody().addProperty(property);
        this.addImports(property.getImports());
        this.getBody().addMethod(get);
        this.getBody().addMethod(set);

        this.genToStringBody(propertyName);
    }

    public void genToStringMehtod() {
        ClassMethod tsMethod = new ClassMethod(Modifier.PUBLIC,
                JavaClass.STRING.getTypeClass(), "toString", null);
        tsMethod.addAnnotations(new Annotation(AnnotationEnum.OVERRIDE));
        ts.insert(0, "\"" + this.getName() + " [").append(" + \"]\"");
        MethodBody body = new MethodBody(new ClassReturn(ts.toString()));
        tsMethod.setBody(body);

        this.getBody().addMethod(tsMethod);
    }

    private void genToStringBody(String property) {
        // ", property: " + property |+ ", property: " + property
        // , property1: propertyValue1|, property2: propertyValue2
        if (ts.length() == 0) {
            ts.append(property).append(": \" + ")
                    .append(property);
        }
        else {
            isAddLine(property);
            ts.append("+ \", ").append(property).append(": \"");

            isAddLine(property);
            ts.append("+ ").append(property);
        }
    }

    private void isAddLine(String property) {
        if (ts.length() + property.length() > tsLength) {
            ts.append(CommonUtils.lineAndIndent(4));
            tsLength = ts.length() + property.length() + tsMaxLength;
        }
        else {
            ts.append(CommonUtils.SPACE);
        }
    }

    public static void main(String[] args) {
        ModelClass model = new ModelClass("Table", "net.hyjuki.demo.model");
        model.addElement("column1_Name", JavaClass.INTEGER.getTypeClass(), "column1注释");
        model.addElement("column_name", JavaClass.STRING.getTypeClass(), "column1注释");
        model.addElement("column2_name", JavaClass.STRING.getTypeClass(), "column1注释");
        model.addElement("column3_name", JavaClass.STRING.getTypeClass(), "column1注释");
        model.addElement("column4_name", JavaClass.DATE.getTypeClass(), "column1注释");
        model.addElement("column5_name", JavaClass.STRING.getTypeClass(), "column1注释");
        model.addElement("column6_name", JavaClass.LIST.getTypeClass(new TypeClass("BaseDao", "net.hyujuki.demo.BaseDao")), "column1注释");
        model.addElement("column7_name", JavaClass.STRING.getTypeClass(), "column1注释");
        model.genToStringMehtod();
        System.out.println(model.formatString());
        System.out.println(model.getTypeClass().getPkgName());
    }
}
