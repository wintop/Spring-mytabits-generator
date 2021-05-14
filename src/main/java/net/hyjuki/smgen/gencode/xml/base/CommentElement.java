package net.hyjuki.smgen.gencode.xml.base;

public class CommentElement extends TextElement {
    @Override
    public String formatString() {
        // 注释不用Normalize
        this.setNormalize(false);
        StringBuilder sb = new StringBuilder();
        sb.append("<!--");
        sb.append(this.getSeperator());
        sb.append(super.formatString());
        sb.append(this.getSeperator());
        sb.append("-->");
        return sb.toString();
    }
}
