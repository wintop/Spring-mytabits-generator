package net.hyjuki.smgen.base.utils;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaConstants {
    public static final String PACKAGE = "package";
    public static final String IMPORT = "import";
    public static final String PACKAGE_SPC = "package ";
    public static final String PUBLIC = "public";
    public static final String CLASS = "class";
    public static final String PRIVATE = "private";
    public static final String IMPLEMENTS = "implements";
    public static final String RETURN = "return";
    public static final String RETURN_SPC = "return ";
    public static final String THIS = "this";
    public static final String VOID = "void";
    public static final String INTERFACE = "interface";
    public static final String TYPE_INT = "int";

    public static final String DIR_JAVA = "java";
    public static final String DIR_MODEL = "model";
    public static final String DIR_DAO = "dao";
    public static final String DIR_SERVICE = "service";
    public static final String DIR_IMPL = "impl";
    public static final String DIR_XML = "mapper";
    public static final String DIR_BASE = "base";
    public static final String DIR_UTILS = "utils";
    public static final String DIR_CONTROLLER = "controller";

    public static final String NAME_GET = "get";
    public static final String NAME_SET = "set";
    public static final String NAME_DAO = "Dao";
    public static final String NAME_SERVICE = "Service";
    public static final String NAME_SERVICE_IMPL = "ServiceImpl";
    public static final String NAME_CONTROLLER = "Controller";

    public static final String LOGGER = Logger.class.getSimpleName();
    public static final String PACKAGE_LOGGER = Logger.class.getName();
    public static final String LOGGER_FACTORY = LoggerFactory.class.getSimpleName();
    public static final String PACKAGE_LOGGER_FACTORY = LoggerFactory.class.getName();

    public static final String ANNOTATION_OVERRIDE = "@Override";
    public static final String ANNOTATION_MAPPER = "@Mapper";
    public static final String ANNOTATION_SERVICE = "@Service";
    public static final String ANNOTATION_AUTOWIRED = "@Autowired";
    public static final String PACKAGE_ANNOTATION_MAPPER = "org.apache.ibatis.annotations.Mapper";
    public static final String PACKAGE_ANNOTATION_AUTOWIRED = "org.springframework.beans.factory.annotation.Autowired";
    public static final String PACKAGE_ANNOTATION_SERVICE = "org.springframework.stereotype.Service";
    public static final String PACKAGE_ANNOTATION_CONTROLLER = "org.springframework.web.bind.annotation.RestController";
    public static final String PACKAGE_ANNOTATION_REQUEST_MAPPING = "org.springframework.web.bind.annotation.RequestMapping";
    public static final String PACKAGE_ANNOTATION_RESPONSE_BODY = "org.springframework.web.bind.annotation.ResponseBody";
    public static final String PACKAGE_ANNOTATION_REQUEST_BODY = "org.springframework.web.bind.annotation.RequestBody";

    public static final String TEMPLATE = "T";
    public static final String LINE_COMMENT = "// ";

    public static final String METHOD_GET = "get";
    public static final String METHOD_FIND = "find";
    public static final String METHOD_SAVE = "save";
    public static final String METHOD_UPDATE = "update";
    public static final String METHOD_REMOVE = "remove";

    public static final String METHOD_SUCCESS = "success";
    public static final String METHOD_FAIL = "fail";

    public static final String NAME_BASE_DAO = "Base" + NAME_DAO;
    public static final String NAME_BASE_SERVICE = "Base" + NAME_SERVICE;
    public static final String NAME_GENERIC_SERVICE = "GenericService";
}
