package net.hyjuki.smgen.java;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.base.JavaConstants;
import net.hyjuki.smgen.java.base.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerClass extends ClassTemplate {
    private String baseName;
    private String serviceName;
    private TypeClass loggerType = new TypeClass(JavaConstants.LOGGER, JavaConstants.PACKAGE_LOGGER);
    private TypeClass model;
    private TypeClass service;
    private TypeClass returnType;
    private TypeClass dataMsgTye;

    private String getMethod;
    private String listMethod;
    private String addMethod;
    private String editMethod;
    private String statusMethod;

    public ControllerClass(String baseName, TypeClass service, String pkgName) {
        super(ClassType.CLASS, baseName + JavaConstants.NAME_CONTROLLER);
        baseName = CommonUtils.upperCase(baseName);
        this.baseName = baseName;
        this.setName(baseName + JavaConstants.NAME_CONTROLLER);
        this.setPkgName(CommonUtils.concatByDot(pkgName, JavaConstants.DIR_CONTROLLER));
//        this.service = new TypeClass(this.serviceName,
//                CommonUtils.concatByDot(pkgName, JavaConstants.DIR_SERVICE, this.serviceName));
//        this.serviceName = baseName + JavaConstants.NAME_SERVICE;
        this.service = service;
        this.serviceName = service.getSimpleName();
        this.model = new TypeClass(baseName, CommonUtils.concatByDot(pkgName, JavaConstants.DIR_MODEL, baseName));
        // 统一的返回值类型
        this.returnType = new TypeClass("HjkResponse",
                CommonUtils.concatByDot(pkgName, JavaConstants.DIR_BASE, JavaConstants.DIR_UTILS, "HjkResponse"));
//        this.returnType = new TypeClass("ResponseMessage",
//                CommonUtils.concatByDot(pkgName, JavaConstants.DIR_BASE, JavaConstants.DIR_UTILS, "ResponseMessage"));
        // 统一的返回错误信息类型
        this.dataMsgTye = new TypeClass("MessageData",
                CommonUtils.concatByDot(pkgName, JavaConstants.DIR_BASE, JavaConstants.DIR_UTILS, "MessageData"));
        this.getMethod = "get" + baseName;
        this.listMethod = "get" + baseName + "List";
        this.addMethod = "add" + baseName;
        this.editMethod = "edit" + baseName;
        this.statusMethod = "edit" + baseName + "Status";

        this.addAnnotation(new Annotation(AnnotationEnum.REST_CONTROLLER));
        this.addAnnotation(new Annotation(AnnotationEnum.REQUEST_MAPPING, CommonUtils.lowerCase(baseName) + "/"));
        this.addImport(this.dataMsgTye.getPkgName());
    }
    /**
     * 1、基本信息
     *      1) 需要引入的类：T, TService,
     * 2、注解类：
     *      1) 头部：RestController， RequestMapping
     *      2) 属性：Autowired
     *      3) 方法：RequestMapping（与1，重复）, RequestBody
     * 3、属性：
     *      1) Logger LoggerFactory
     *      2) 同名Service
     * 4、方法：
     *      1) get add edit del edit_status
     *      2) 返回类型：HjkResposne? ResponseMessage<T>?
     *      3) 日志：logger.info("====function name(url name)，param: {}", param)
     *      4) return reponse.success();
     */
    public void genClass() {
        ClassBody body = new ClassBody();
        // 设置属性
        // Logger logger = LoggerFactory.getLogger(Controller.class);
        Property logger = new Property(Modifier.PRIVATE_STATIC_FINAL, loggerType,
                CommonUtils.lowerCase(loggerType.getSimpleName()));
        List<Parameter> paramClass = new ArrayList<>();
        paramClass.add(new Parameter(this.getName(), this.getName() + ".class"));
        Method getLogMehod = new Method(JavaConstants.LOGGER_FACTORY + ".getLogger", paramClass);
        this.addImport(JavaConstants.PACKAGE_LOGGER_FACTORY);
        logger.setValue(getLogMehod.formatString());

        // 设置service定义
        Property modelService = new Property(this.service, CommonUtils.lowerCase(this.service.getSimpleName()));
        modelService.addAnnotation(new Annotation(AnnotationEnum.AUTOWIRED));

        body.addProperty(logger);
        body.addProperty(modelService);

        // 定义类中的方法
        this.setMethods(body);
        this.setBody(body);
    }

    /**
     * 设置该类的主体部分
     */
    public void setMethods(ClassBody body) {
        // 方法定义
        List<Parameter> paramId = new ArrayList<>();
        List<Parameter> paramModel = new ArrayList<>();
        List<Parameter> paramStatus = new ArrayList<>();

//        for (PrimaryKey key: keys) {
//            paramId.add(new Parameter(key.getColumnName(), DataType.getName(key.getDateType())));
//        }
        // get 参数
        paramId.add(new Parameter(Long.class.getSimpleName(), "id"));
        // add edit list 参数
        Parameter param = new Parameter(this.model,
                CommonUtils.lowerCase(this.model.getSimpleName()),
                new Annotation(AnnotationEnum.REQUEST_BODY));
        paramModel.add(param);
        this.addImports(param.getImports());

        // edit_status 参数
        paramStatus.addAll(paramId);
        paramStatus.add(new Parameter(Byte.class.getSimpleName(), "status"));

        // get方法：public T get(Number id);
        body.addMethod(setClassMethod(this.getMethod, BaseMethod.get.name(), paramId, this.model, BaseMethod.get));
        body.addMethod(setClassMethod(this.listMethod, BaseMethod.list.name(), paramModel,
                JavaClass.LIST.getTypeClass(this.model), BaseMethod.find));
        body.addMethod(setClassMethod(this.addMethod, BaseMethod.add.name(), paramModel,
                JavaClass.INTEGER.getTypeClass(), BaseMethod.save));
        body.addMethod(setClassMethod(this.editMethod, BaseMethod.edit.name(), paramModel,
                JavaClass.LIST.getTypeClass(this.model), BaseMethod.update));
        body.addMethod(setClassMethod(this.statusMethod, BaseMethod.edit_status.name(), paramStatus,
                this.model, BaseMethod.update));
    }

    /**
     * 生成方法
     * @param methodName
     * @param urlName
     * @param parameters
     * @param template
     * @param serviceMethod
     * @return
     */
    private ClassMethod setClassMethod(String methodName, String urlName, List<Parameter> parameters,
        TypeClass template, BaseMethod serviceMethod) {
        // 使用ResponseMessage用，如果使用HjkResponse就不用这个了
//        this.returnType.setTemplate(template);
        // 方法的定义部分
        ClassMethod method = new ClassMethod(Modifier.PUBLIC, this.returnType, methodName, parameters);
        // 方法需要注解@RequestMapper("path")
        method.addAnnotations(new Annotation(AnnotationEnum.REQUEST_MAPPING, urlName));

        // 方法的主体 日志，判断，执行service中的方法，return
        // 日志
        MethodBody methodBody = new MethodBody(logExec(methodName, urlName, parameters));
        // 定义返回值
        methodBody.appendBody(CommonUtils.line());
        // 使用ResponseMessage用，如果使用HjkResponse就不用这个了
//        methodBody.appendBody(defineObject(this.returnType));
        // 参数判断
        methodBody.appendBody(CommonUtils.lineAndIndent(2));
        if (!CommonUtils.isEmpty(parameters)) {
            methodBody.appendCheckParam(parameters, this.returnType, this.dataMsgTye);
        }

        if (this.statusMethod.equals(methodName)) {
            methodBody.appendBody(CommonUtils.line());
            methodBody.appendBody(CommonUtils.lineAndIndent(2));
            methodBody.appendBody(defineObject(this.model));
            String modName = CommonUtils.lowerCase(this.model.getSimpleName());
            for (Parameter parameter: parameters) {
                String setName = CommonUtils.genSetMethodName(parameter.getVariable());
                Method setMethod = new Method(modName, setName, parameter);
                methodBody.appendBody(CommonUtils.lineAndIndent(2));
                methodBody.appendBody(setMethod);
            }

            // 返回值主体
            methodBody.appendBody(CommonUtils.lineAndIndent(2));
            ClassReturn classReturn = new ClassReturn(
                    new Method(CommonUtils.concatByDot(this.returnType.getSimpleName(),
                            JavaConstants.METHOD_SUCCESS),
                            new Method(CommonUtils.concatByDot(CommonUtils.lowerCase(this.serviceName),
                                    serviceMethod.name()), CommonUtils.lowerCase(this.model.getSimpleName()))));
            methodBody.appendBody(classReturn);
        }
        else {
            // 返回值主体
            methodBody.appendBody(CommonUtils.lineAndIndent(2));
            ClassReturn classReturn = new ClassReturn(
                    new Method(CommonUtils.concatByDot(this.returnType.getSimpleName(),
                            JavaConstants.METHOD_SUCCESS),
                            new Method(CommonUtils.concatByDot(CommonUtils.lowerCase(this.serviceName),
                                    serviceMethod.name()), parameters)));
            methodBody.appendBody(classReturn);
        }

        // 添加到body
        method.setBody(methodBody);

        return method;
    }

    // logger.info("========edit(edit) menu: {}", menu);
    private String logExec(String methodName, String urlName, List<Parameter> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append(CommonUtils.lowerCase(this.loggerType.getSimpleName()))
                .append(".info(").append("\"====")
                .append(methodName).append("(")
                .append(urlName).append(")");
        if (!CommonUtils.isEmpty(parameters)) {
            for (Parameter param: parameters) {
                sb.append(", ").append(param.getVariable())
                        .append(": {}");
            }
            sb.append("\"");
            for (Parameter param: parameters) {
                sb.append(", ").append(param.getVariable());
            }
            sb.append(");");
        }
        else {
            sb.append("\");");
        }

        return sb.toString();
    }

    // ReponseMessage<T> responseMessage = new ResponseMessage<T>();
    private String defineObject(TypeClass typeClass) {
        StringBuffer sb = new StringBuffer();
        sb.append(typeClass.getClassName())
                .append(" ")
                .append(CommonUtils.lowerCase(typeClass.getSimpleName()))
                .append(" = new ").append(typeClass.getClassName())
                .append("();");

        return sb.toString();
    }

    public static void main(String[] args) {
        TypeClass service = new TypeClass("MenuService", "net.hjk.test.service.MenuService");
        ControllerClass controller = new ControllerClass("Menu", service, "net.hjk.test");
        controller.genClass();
        System.out.println(controller.formatString());
    }
}
