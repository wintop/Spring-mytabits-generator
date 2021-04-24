package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成类
 */
public class ClassTemplate extends ClassDefine {
    private List<Annotation> annotations;
    private ClassBody body;

    public ClassTemplate(ClassType classType, String name) {
        super(Modifier.PUBLIC, classType, name);
    }

    public ClassTemplate(Modifier modifier, ClassType type, String name) {
        super(modifier, type, name);
    }

    public ClassTemplate(Modifier modifier, ClassType type, String name, String template,
                         TypeClass extend, TypeClass implement) {
        super(modifier, type, name, template, extend, implement);
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(Annotation annotation) {
        if (this.annotations == null) {
            this.annotations = new ArrayList<Annotation>();
        }
        this.annotations.add(annotation);
        this.addImports(annotation.getImports());
    }

    @Override
    public void setExtend(TypeClass extend) {
        super.setExtend(extend);
        this.addImport(extend);
    }

    @Override
    public void setImplement(TypeClass impl) {
        super.setImplement(impl);
        this.addImport(impl);
    }

    public ClassBody getBody() {
        return body;
    }

    public void setBody(ClassBody body) {
        this.body = body;
        this.addImports(body.getImports());
    }

    public String formatImports() {
        return this.getImports().formatString();
    }

    private boolean isSamePkg(Import impt) {
        String cls = impt.getValue();
        if(cls.startsWith(this.pkgName.getName() + ".")) {
            if(cls.indexOf('.', this.pkgName.getName().length()+1) == -1) {
                return true;
            }
            return false;
        }
        return false;
    }

    private int removeSameImport() {
        List<Import> impts = new ArrayList<>();
        if (!CommonUtils.isEmpty(this.getImports().get())) {
            for (Import impt: this.getImports().get()) {
                if (this.isSamePkg(impt)) {
                    impts.add(impt);
                }
            }

            int size = impts.size();
            if (size > 0) {
                for (Import impt: impts) {
                    this.removeImport(impt);
                }
            }
            return size;
        }

        return 0;
    }

    public String formatClassDefine() {
        return super.formatString();
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();

        // 删除imports中和自己在同一个包中的import
        this.removeSameImport();

        sb.append(this.getPkgName().getPackage()).append(";")
                .append(CommonUtils.line(2))
                .append(this.getImports().formatString());

        if (!CommonUtils.isEmpty(this.comment)) {
            sb.append("/**")
                    .append(CommonUtils.line())
                    .append(" * ").append(this.comment)
                    .append(CommonUtils.line())
                    .append(" */")
                    .append(CommonUtils.line());
        }
        if (!CommonUtils.isEmpty(this.getAnnotations())) {
            sb.append(CommonUtils.formatList(this.getAnnotations()));
        }

        sb.append(this.formatClassDefine())
                .append(" {")
                .append(CommonUtils.line())
                .append(this.getBody().formatString())
                .append("}");

        return sb.toString();
    }
}
