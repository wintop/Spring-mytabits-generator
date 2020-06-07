package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.DbDataType;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement implements Element {
    protected String seperator;
    protected String comment;
    protected List<String> annotations = new ArrayList<>();
    public DbDataType dataType = DbDataType.getInstance();

    public String getSeperator() {
        return seperator;
    }

    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    public void setComment(String comment) {
        if (CommonUtils.strIsEmpty(comment.trim())) {
            this.comment = "";
        }
        else {
            this.comment = "/* " + comment + " */";
        }
    }

    public String getComment() {
        return comment;
    }

    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public String formatCommentAnnotations(int count) {
        StringBuilder sb = new StringBuilder();
        String line = CommonUtils.lineAndIndent(count);
        if (!CommonUtils.strIsEmpty(this.getComment())) {
            sb.append(this.getComment());
            sb.append(line);
        }

        if (annotations.size() > 0) {
            for (String annotation: annotations) {
                sb.append(annotation).append(line);
            }
        }
        return sb.toString();
    }

    public String listFormatString(List<? extends Element> elements, String seperator) {
        if (elements == null || elements.size() ==0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Element element: elements) {
            sb.append(seperator)
                    .append(element.formatString());
        }

        return sb.toString();
    }
}
