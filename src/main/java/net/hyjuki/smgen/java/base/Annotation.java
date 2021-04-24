package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;

public class Annotation extends Element {
    private AnnotationEnum annotation;
    private String value;

    public Annotation(AnnotationEnum annotation, String value) {
        this.annotation = annotation;
        this.value = value;
        this.addImport(annotation.getAnnotPackage());
    }

    public Annotation(AnnotationEnum annotation) {
        this.annotation = annotation;
        this.addImport(annotation.getAnnotPackage());
    }

    public AnnotationEnum getAnnotation() {
        return annotation;
    }

    @Override
    public String formatString() {
        if (CommonUtils.isEmpty(this.value)) {
            return annotation.formatAnnotation();
        }
        return annotation.formatAnnotation(value);
    }

    public static void main(String[] args) {
        Annotation annotation = new Annotation(AnnotationEnum.REST_CONTROLLER, "abc");
        System.out.println(annotation.annotation.formatAnnotation(annotation.value));
    }
}
