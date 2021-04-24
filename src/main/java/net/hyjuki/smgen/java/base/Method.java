package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法类定义
 * \@Annotation1
 * \@Annotation2
 * public void methodName(Param param1, Param param2)
 */
public class Method extends Element {
    // 如：@Override
    List<Annotation> annotations;
    // 如：public/private
    Modifier modifier;
    // 如：Integer/void
    TypeClass type;
    // 如：methodName
    String name;
    // 21-03-16新增：方法的对象
    String objName;
    // 如：(ClassName1 name1)
    List<Parameter> parameters;
    // 缩进
    int indent = 1;
    boolean isExec = false;

    public Method() {
        super();
    }

    /**
     * Method构造函数，生成一个执行的方法
     * 执行方法不需要引入类（根据操作逻辑，应该已经提前引入了）
     * @param name
     * @param parameters
     */
    public Method(String name, List<Parameter> parameters) {
        this.name = name;
        this.parameters = parameters;
        this.isExec = true;
    }

    /**
     * Method构造函数，生成一个执行的方法，只有一个参数的情况
     * 执行方法不需要引入类（根据操作逻辑，应该已经提前引入了）
     * @param name
     * @param parameter
     */
    public Method(String name, String parameter) {
        this.name = name;
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(new Parameter("", parameter));
        this.isExec = true;
    }

    public Method(String name, Method method) {
        this.name = name;
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(new Parameter("", method.formatString()));
        this.addImports(method.getImports());
        this.isExec = true;
    }

    /**
     * 一个完整的方法，参数也是方法
     * @param objName
     * @param name
     * @param parameter
     */
    public Method(String objName, String name, Parameter parameter) {
        this.name = name;
        this.objName = objName;
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        this.isExec = true;
    }

    /**
     * Method构造函数，定义一个不带修饰符的方法
     * @param type
     * @param name
     * @param parameters
     */
    public Method(TypeClass type, String name, List<Parameter> parameters) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.addImport(type);
        if (!CommonUtils.isEmpty(parameters)) {
            for (Parameter parameter: parameters) {
                this.addImport(parameter.getType());
            }
        }
}

    /**
     * Method构造函数，完整的构建
     * @param modifier
     * @param type
     * @param name
     * @param parameters
     */
    public Method(Modifier modifier, TypeClass type,
                  String name, List<Parameter> parameters) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.addImport(type);
        if (!CommonUtils.isEmpty(parameters)) {
            for (Parameter parameter: parameters) {
                this.addImport(parameter.getType());
            }
        }
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public TypeClass getType() {
        return type;
    }

    public void setType(TypeClass type) {
        this.type = type;
        this.addImport(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public String indent() {
        return CommonUtils.indent(this.indent);
    }

    public void addAnnotations(Annotation annotation) {
        if (this.annotations == null) {
            this.annotations = new ArrayList<>();
        }
        this.annotations.add(annotation);
        this.addImports(annotation.getImports());
    }

    public void addParameter(Parameter parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        this.addImports(parameter.getImports());
    }

    public void addParameter(TypeClass paramType, String paramValue) {
        Parameter parameter = new Parameter(paramType, paramValue);
        this.addParameter(parameter);
        this.addImport(paramType);
    }

    public boolean isExec() {
        return isExec;
    }

    public void setExec(boolean exec) {
        isExec = exec;
    }

    public String getExecMethod() {
        if (CommonUtils.isEmpty(this.objName)) {
            return this.getName() + getExecParameterList();
        }
        return CommonUtils.concatByDot(this.objName, this.getName()) + getExecParameterList();
    }

    public String getDefineMethod() {
        StringBuilder sb = new StringBuilder();
        if (this.getModifier() != null) {
            sb.append(this.getModifier().modifier())
                    .append(CommonUtils.SPACE);
        }
        sb.append(this.getType().getClassName()).append(CommonUtils.SPACE)
                .append(this.getName())
                .append(this.getParameterList());
        return sb.toString();
    }

    private String getParameterList() {
        String paramString = "(";
        if (!CommonUtils.isEmpty(this.parameters)) {
            for (Parameter parameter: this.parameters) {
                paramString += parameter.formatString() + ", ";
            }

            paramString = paramString.substring(0, paramString.length()-2);
        }
        paramString += ")";

        return paramString;
    }

    private String getExecParameterList() {
        String paramString = "(";
        if (!CommonUtils.isEmpty(this.parameters)) {
            for (Parameter parameter: this.parameters) {
                paramString += parameter.getVariable() + ", ";
            }

            paramString = paramString.substring(0, paramString.length()-2);
        }
        paramString += ")";

        return paramString;
    }

    @Override
    public String formatString() {
        /*
         如果Mehtod的type(void, int, Object等)说明是一个执行方法
         */
        if (this.isExec()) {
            return this.getExecMethod();
        }
        else {
            return CommonUtils.indent(1) + this.getDefineMethod()
                    + CommonUtils.SEMICOLON;
        }
    }

    // public int methodName(Parameter param);
    public static void main(String[] args) {
        Method method = new Method();
        method.setName("methodName");
        method.setType(JavaClass.STRING.getTypeClass());
        System.out.println(method.indent() + method.getDefineMethod());
        System.out.println(method.indent() + method.getExecMethod());
        System.out.println(CommonUtils.line().length());
    }
}
