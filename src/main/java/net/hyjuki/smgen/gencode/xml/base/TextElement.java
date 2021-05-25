package net.hyjuki.smgen.gencode.xml.base;

import java.util.ArrayList;
import java.util.List;

public class TextElement implements Element<String> {
    private List<String> elements = new ArrayList<>();
    // 分隔符（换行，制表符，换行符+制表符）
    private String seperator;
    // 转义特殊字符
    private boolean normalize = true;

    @Override
    public String getTagName() {
        return "";
    }

    @Override
    public List<Attribute> getAttributes() {
        return null;
    }

    @Override
    public void addAttribute(String name, String value) {
    }

    @Override
    public void addAttribute(Attribute attribute) {
        this.addAttribute(attribute);
    }

    @Override
    public String getSeperator() {
        return seperator;
    }

    @Override
    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean hasChildElements() {
        return false;
    }

    @Override
    public boolean addElement(String element) {
        return elements.add(element);
    }

    @Override
    public boolean addElements(List<String> nodes) {
        return elements.addAll(elements);
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;
    }

    protected String normalize(String s, boolean normalize) {
        if (!normalize) {
            return s;
        }

        StringBuilder text = new StringBuilder();
        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            normalize(text, c);
        }

        return text.toString();
    }

    private void handleLineFeed(StringBuilder text) {
        // If LF is part of the document's content, it
        // should be printed back out with the system default
        // line separator.  XML parsing forces \n only after a parse,
        // but we should write it out as it was to avoid whitespace
        // commits on some version control systems.
        text.append(System.getProperty("line.separator")); //$NON-NLS-1$
    }

    private void handleCarriageReturn(StringBuilder text) {
        // If CR is part of the document's content, it
        // must be printed as a literal otherwise
        // it would be normalized to LF when the document
        // is reparsed.
        text.append("&#xD;"); //$NON-NLS-1$
    }

    private void handleDoubleQuote(StringBuilder text) {
        // A '"' that appears in character data
        // does not need to be escaped.
        text.append("&quot;"); //$NON-NLS-1$
    }

    private void handleAmpersand(StringBuilder text) {
        text.append("&amp;"); //$NON-NLS-1$
    }

    private void handleGreaterThan(StringBuilder text) {
        text.append("&gt;"); //$NON-NLS-1$
    }

    private void handleLessThan(StringBuilder text) {
        text.append("&lt;"); //$NON-NLS-1$
    }

    protected void normalize(StringBuilder text, char c) {

        switch (c) {
            case '<':
                handleLessThan(text);
                break;
            case '>':
                handleGreaterThan(text);
                break;
            case '&':
                handleAmpersand(text);
                break;
            case '"':
                handleDoubleQuote(text);
                break;
            case '\r':
                handleCarriageReturn(text);
                break;
            case '\n':
                handleLineFeed(text);
                break;
            default:
                text.append(c);
        }
    }
    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        if (elements == null) {
            return "";
        }
        int size = elements.size();
        if (size == 0) {
            return "";
        }
        size = size - 1;
        if (size == 0) {
            return normalize(elements.get(0), normalize);
        }
        for (int i = 0; i < size; i++) {
            sb.append(normalize(elements.get(i), normalize))
                    .append(seperator);
        }
        sb.append(normalize(elements.get(size), normalize));

        return sb.toString();
    }
}
