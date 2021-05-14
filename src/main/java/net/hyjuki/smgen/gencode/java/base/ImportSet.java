package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

import java.util.Set;
import java.util.TreeSet;

public class ImportSet {
    Set<Import> imports;

    public void add(Import element) {
        if (this.imports == null) {
            imports = new TreeSet<>();
        }
        this.imports.add(element);
    }

    public void add(Set<Import> element) {
        if (!GenUtils.isEmpty(element)) {
            if (this.imports == null) {
                imports = new TreeSet<>();
            }
            this.imports.addAll(element);
        }
    }

    public void add(String element) {
        if (!GenUtils.isEmpty(element)) {
            if (this.imports == null) {
                imports = new TreeSet<>();
            }
            this.imports.add(new Import(element));
        }
    }

    public Set<Import> get() {
        return this.imports;
    }

    public boolean remove(Import impt) {
        return this.imports.remove(impt);
    }

    public String formatString() {
        StringBuilder sb = new StringBuilder();
        String line = GenUtils.line();

        String importRoot = "";
        if (!GenUtils.isEmpty(this.imports)){
            for (Import impt: this.imports) {
                String root = impt.getValue().split("\\.")[0];
                // 如果根目录不同，按照IDEA的习惯多加一个空行
                if (!"".equals(importRoot) && !importRoot.equals(root)) {
                    sb.append(line);
                }
                sb.append(impt.formatString());
                importRoot = root;
                sb.append(line);
            }
            sb.append(line);
        }

        return sb.toString();
    }
}
