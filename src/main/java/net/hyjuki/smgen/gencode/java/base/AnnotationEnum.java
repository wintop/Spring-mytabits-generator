package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;

/**
 * 注解和注解需要引入的包
 * 没有参数？？
 */
public enum AnnotationEnum {
    OVERRIDE("Override", ""),
    MAPPER("Mapper", JavaConstants.PACKAGE_ANNOTATION_MAPPER),
    SERVICE("Service", JavaConstants.PACKAGE_ANNOTATION_SERVICE),
    AUTOWIRED("Autowired", JavaConstants.PACKAGE_ANNOTATION_AUTOWIRED),
    REST_CONTROLLER("RestController", JavaConstants.PACKAGE_ANNOTATION_CONTROLLER),
    REQUEST_MAPPING("RequestMapping", JavaConstants.PACKAGE_ANNOTATION_REQUEST_MAPPING),
    REQUEST_BODY("RequestBody", JavaConstants.PACKAGE_ANNOTATION_REQUEST_BODY),
    RESPONSE_BODY("ResponseBody", JavaConstants.PACKAGE_ANNOTATION_RESPONSE_BODY);

    private String annotation;
    private String annotPackage;

    AnnotationEnum(String annotation, String annotPackage) {
        this.annotation = annotation;
        this.annotPackage = annotPackage;
    }

    public String formatAnnotation() {
        return "@" + this.annotation;
    }

    public String formatAnnotation(String value) {
        return "@" + this.annotation + "(\"" + value + "\")";
    }

    public String getAnnotPackage() {
        if (GenUtils.isEmpty(this.annotation)) {
            return "";
        }
        return this.annotPackage;
    }
}
