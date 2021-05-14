package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

public abstract class Element {
    ImportSet imports = new ImportSet();

    public String formatString() {
        return "";
    }

    public ImportSet getImports() {
        return this.imports;
    }

    public void addImport(String impt) {
        this.imports.add(impt);
    }

    void removeImport(Import impt) {
        this.imports.remove(impt);
    }

    void addImport(TypeClass type) {
        if (!GenUtils.isEmpty(type.getPkgName())){
            this.imports.add(type.getPkgName());
            if (type.getTemplate() != null
                    && type.getTemplate().getClassName() != null) {
                this.imports.add(type.getTemplate().getPkgName());
            }
        }
    }

    protected void addImports(ImportSet imports) {
        this.imports.add(imports.get());
    }
}
