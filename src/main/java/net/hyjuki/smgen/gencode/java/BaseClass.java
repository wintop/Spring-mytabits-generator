package net.hyjuki.smgen.gencode.java;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.gencode.java.base.*;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseClass extends ClassTemplate {
    // 自己的类信息
    TypeClass typeClass;

    // 参数信息：id用于get，remove, T(Template)用于save和update
    List<Parameter> paramId = new ArrayList<>();
    List<Parameter> paramT = new ArrayList<>();

    /**
     * 默认都是pubilc的类或者接口
     * @param type 类型：class / abstract class / interface
     * @param name 名称
     * @param pkgName 包名，即：groupId
     */
    BaseClass(ClassType type, String name, String pkgName) {
        super(type, name);
        paramId.add(new Parameter(JavaClass.SERIALIZABLE.getTypeClass(), "id"));
        paramT.add(new Parameter(JavaClass.TEMPLATE.getTypeClass(), "t"));

        this.setPkgName(pkgName);
        this.typeClass = new TypeClass(name, GenUtils.concatByDot(pkgName, name),
                    new TypeClass(this.getTemplate()));
    }

    public TypeClass getTypeClass() {
        return this.typeClass;
    }
}
