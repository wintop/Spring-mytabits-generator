package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成类的内容，即大括号中的内容
 */
public class ClassBody extends Element {
    private List<Property> properties;
    private List<Method> methods;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }

        this.properties.add(property);
        this.addImports(property.getImports());
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method method) {
        if (this.methods == null) {
            this.methods = new ArrayList<>();
        }
        this.methods.add(method);
        this.addImports(method.getImports());
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();

        if (!GenUtils.isEmpty(this.getProperties())) {
            sb.append(GenUtils.formatList(this.getProperties()));
        }

        sb.append(GenUtils.formatList(this.getMethods()));

        return sb.toString();
    }
}
