package net.hyjuki.smgen.java;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.java.base.*;

import java.util.List;

public class GenericService extends BaseClass {
    GenericService(String name, String pkgName) {
        super(ClassType.ABSTRACT, name, pkgName);
        this.setTemplate(JavaClass.TEMPLATE.getName());
    }

    Method getBase;

    /**
     * 生成定义方法
     * @param type
     * @param name
     * @param parameters
     * @return
     */
    private ClassMethod setClassMethod(TypeClass type, String name, List<Parameter> parameters) {
        String retMethod = getBase.getExecMethod();
        ClassMethod method = new ClassMethod(Modifier.PUBLIC, type, name, parameters);
        method.addAnnotations(new Annotation(AnnotationEnum.OVERRIDE));
        method.setBody(new MethodBody(new Method(CommonUtils.concatByDot(retMethod, name), parameters)));

        return method;
    }

    /**
     * 设置该类的主体部分
     * @param baseDao
     * @param pkgName
     */
    public void setBody(String baseDao, String pkgName) {
        ClassBody body = new ClassBody();

        // public abstract BaseDao<T> getBaseDao();
        TypeClass type = new TypeClass(baseDao, pkgName,
                JavaClass.TEMPLATE.getTypeClass());
        this.getBase = new Method(Modifier.PUBLIC_ABSTRACT, type,
                CommonUtils.genGetMethodName(baseDao), null);

        body.addMethod(this.getBase);

        // get方法：public T get(Number id);
        body.addMethod(setClassMethod(JavaClass.TEMPLATE.getTypeClass(),
                BaseMethod.get.name(), this.paramId));
        // find方法：public List<T> find(T t);
        body.addMethod(setClassMethod(JavaClass.LIST.getTypeClass(),
                BaseMethod.find.name(), this.paramT));
        // save方法: public int save(T t);
        body.addMethod(setClassMethod(JavaClass.INT.getTypeClass(),
                BaseMethod.save.name(), this.paramT));
        // update方法: public int update(T t);
        body.addMethod(setClassMethod(JavaClass.INT.getTypeClass(),
                BaseMethod.update.name(), this.paramT));
        // remove方法: public int remove(Number id);
        body.addMethod(setClassMethod(JavaClass.INT.getTypeClass(),
                BaseMethod.remove.name(), this.paramId));

        this.setBody(body);
    }

    public static void main(String[] args) {
        GenericService service = new GenericService("GenericService", "net.hyjuki.demo.base");
        TypeClass impl =   new TypeClass("BaseService", "net.hyjuki.demo.base.BaseService", JavaClass.TEMPLATE.getTypeClass());
        service.setExtend(impl);

        service.setBody("BaseDao", "net.hyjuki.demo.base.dao.BaseDao");

        System.out.println(service.formatString());
    }
}
