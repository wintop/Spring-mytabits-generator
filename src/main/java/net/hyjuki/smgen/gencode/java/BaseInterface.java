package net.hyjuki.smgen.gencode.java;

import net.hyjuki.smgen.base.utils.JavaConstants;
import net.hyjuki.smgen.gencode.java.base.*;

public class BaseInterface extends BaseClass {

    public BaseInterface(String name, String pkgName) {
        super(ClassType.INTERFACE, name, pkgName);
    }

    private void genMethods() {
        ClassBody body = new ClassBody();

        Method getMethod = new Method(JavaClass.TEMPLATE.getTypeClass(),
                BaseMethod.get.name(), paramId);
        Method findMethod = new Method(JavaClass.LIST.getTypeClass(),
                BaseMethod.find.name(), paramT);
        Method saveMethod = new Method(JavaClass.INT.getTypeClass(), BaseMethod.save.name(), paramT);
        Method updateMethod = new Method(JavaClass.INT.getTypeClass(), BaseMethod.update.name(), paramT);
        Method removeMthod = new Method(JavaClass.INT.getTypeClass(), BaseMethod.remove.name(), paramId);

        body.addMethod(getMethod);
        body.addMethod(findMethod);
        body.addMethod(saveMethod);
        body.addMethod(updateMethod);
        body.addMethod(removeMthod);

        this.setBody(body);
    }

    public void genBaseInterface() {
        this.setTemplate(JavaConstants.TEMPLATE);
        this.genMethods();
    }

    public void genInterface(TypeClass extend) {
        this.setExtend(extend);
        this.setBody(new ClassBody());
    }

    public static void main(String[] args) {
        BaseInterface baseInterface = new BaseInterface("BaseService", "net.hyjuki.demo");
        baseInterface.genBaseInterface();

        System.out.println(baseInterface.formatString());
    }
}
