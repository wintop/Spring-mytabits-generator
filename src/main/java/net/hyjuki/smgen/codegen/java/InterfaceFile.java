package net.hyjuki.smgen.codegen.java;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.MethodConfig;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.java.base.ClassFile;
import net.hyjuki.smgen.codegen.java.base.DefineMethod;
import net.hyjuki.smgen.codegen.java.base.JavaConstants;
import net.hyjuki.smgen.codegen.java.base.Parameter;

import java.util.ArrayList;
import java.util.List;

public class InterfaceFile extends ClassFile {
    protected String beanPackage;
    // 方法，没有进一步对象化；如果想支持新的方法，在此添加
    private DefineMethod getMedthod;
    private DefineMethod findMethod;
    private DefineMethod saveMethod;
    private DefineMethod updateMethod;
    private DefineMethod removeMethod;
    // 方法名称
    // 参数格式
    private List<Parameter> keyParameters;
    private Parameter modelParameter;

    public InterfaceFile(String packageName, Table table) {
        super(packageName, table);
    }

    /**
     * 统一引入包名，方便调整顺序，后期可以优化
     */
    public void setImportPackages() {
        // JavaBean package
        this.addImportPackage(beanPackage);
        // import List package
        this.addImportPackage(List.class.getName());
    }

    /**
     * 依命名规则，设置完整的类名
     * @param suffix inteface的名称后缀，如：Mapper，Dao，Service
     */
    public void setInterfaceName(String suffix) {
        this.setClassName(this.modelName + CommonUtils.upperCase(suffix));
    }

    /**
     * 设置 Javabean 的包名，默认为：net.hyjuki.model。
     * @param beanPackage javabean的包名
     * @return javabean的完整包名，如：net.hyjuki.model.bean(Bean所在包)
     */
    public String setBeanPackage(String beanPackage) {
        this.beanPackage = CommonUtils.concatPackage(this.packageName, beanPackage,
                this.modelName);
        return this.beanPackage;
    }

    public List<Parameter> getKeyParameters() {
        return keyParameters;
    }

    /**
     * 根据Table的Primary Key设置参数
     * 改参数为定义时所用参数，如：new Parameter(Long id), new Parameter(String code)
     */
    public void setKeyParameters() {
        this.keyParameters = new ArrayList<>();

        for (PrimaryKey key: this.keys) {
            this.keyParameters.add(new Parameter(dataType.getName(key.getDateType()),
                    CommonUtils.getProperty(key.getColumnName())));
        }
    }

    public Parameter getModelParameter() {
        return modelParameter;
    }

    /**
     * 设置以JavaBean作为类型的参数，一般只有一个参数
     * 如：Model model 或者 JavaBean javaBean;
     */
    public void setModelParameter() {
        this.modelParameter = new Parameter(modelName, CommonUtils.lowerCase(modelName));
    }

    public DefineMethod getGetMedthod() {
        return getMedthod;
    }

    /**
     * 设置get方法
     * 此处没有方法实现
     * @param medthodName 方法名称
     */
    public void setGetMedthod(String medthodName) {
        this.getMedthod = new DefineMethod();
        getMedthod.setType(this.modelName);
        getMedthod.setMethod(medthodName);
        getMedthod.addParameters(keyParameters);
    }

    /**
     * 设置get方法
     * 此处没有方法实现
     */
    public void setGetMedthod() {
        this.setGetMedthod(JavaConstants.METHOD_GET);
    }


    public DefineMethod getFindMethod() {
        return findMethod;
    }

    /**
     * 设置find方法
     * 此处没有方法实现
     * @param methodName find方法名称
     */
    public void setFindMethod(String methodName) {
        this.findMethod  = new DefineMethod();
        this.findMethod.setType(this.findMethod.getListType(modelName));
        this.findMethod.setMethod(methodName);
        this.findMethod.addParameter(this.modelParameter);
    }

    /**
     * 设置find方法
     * 此处没有方法实现
     */
    public void setFindMethod() {
        this.setFindMethod(JavaConstants.METHOD_FIND);
    }

    public DefineMethod getSaveMethod() {
        return saveMethod;
    }

    /**
     * 设置save方法
     * 此处没有方法实现
     * @param methodName
     */
    public void setSaveMethod(String methodName) {
        this.saveMethod = new DefineMethod();
        this.saveMethod.setType(JavaConstants.TYPE_INT);
        this.saveMethod.setMethod(methodName);
        this.saveMethod.addParameter(this.modelParameter);
    }

    /**
     * 设置save方法
     * 此处没有方法实现
     */
    public void setSaveMethod() {
        this.setSaveMethod(JavaConstants.METHOD_SAVE);
    }

    public DefineMethod getUpdateMethod() {
        return updateMethod;
    }

    /**
     * 设置upate方法
     * 此处没有方法实现
     * @param methodName
     */
    public void setUpdateMethod(String methodName) {
        this.updateMethod = new DefineMethod();
        this.updateMethod.setType(JavaConstants.TYPE_INT);
        this.updateMethod.setMethod(methodName);
        this.updateMethod.addParameter(modelParameter);
    }

    /**
     * 设置upate方法
     * 此处没有方法实现
     */
    public void setUpdateMethod() {
        this.setUpdateMethod(JavaConstants.METHOD_UPDATE);
    }

    public DefineMethod getRemoveMethod() {
        return removeMethod;
    }

    /**
     * 设置remove方法
     * 此处没有方法实现
     * @param methodName
     */
    public void setRemoveMethod(String methodName) {
        this.removeMethod = new DefineMethod();
        removeMethod.setType(JavaConstants.TYPE_INT);
        removeMethod.setMethod(methodName);
        removeMethod.addParameters(this.keyParameters);
    }

    /**
     * 设置remove方法
     * 此处没有方法实现
     */
    public void setRemoveMethod() {
        this.setRemoveMethod(JavaConstants.METHOD_REMOVE);
    }

    public void setMethods(MethodConfig methods) {
        this.setKeyParameters();
        this.setModelParameter();

        if (!CommonUtils.strIsEmpty(methods.getGetName())) {
            this.setGetMedthod();
            this.addMethod(getMedthod);
        }
        if (!CommonUtils.strIsEmpty(methods.getFindName())) {
            this.setFindMethod();
            this.addMethod(findMethod);
        }
        if (!CommonUtils.strIsEmpty(methods.getSaveName())) {
            this.setSaveMethod();
            this.addMethod(saveMethod);
        }
        if (!CommonUtils.strIsEmpty(methods.getUpdateName())) {
            this.setUpdateMethod();
            this.addMethod(updateMethod);
        }
        if (!CommonUtils.strIsEmpty(methods.getRemoveName())) {
            this.setRemoveMethod();
            this.addMethod(removeMethod);
        }
    }

    /**
     * 默认的方法设置
     */
    public void setMethods() {
        this.setKeyParameters();
        this.setModelParameter();
        this.setGetMedthod();
        this.addMethod(getMedthod);
        this.setFindMethod();
        this.addMethod(findMethod);
        this.setSaveMethod();
        this.addMethod(saveMethod);
        this.setUpdateMethod();
        this.addMethod(updateMethod);
        this.setRemoveMethod();
        this.addMethod(removeMethod);
    }
}
