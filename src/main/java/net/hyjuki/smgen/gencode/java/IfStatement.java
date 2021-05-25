package net.hyjuki.smgen.gencode.java;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.gencode.java.base.Parameter;
import net.hyjuki.smgen.gencode.java.base.TypeClass;

import java.util.List;

public class IfStatement {
    private static String strIf = "if";

    public static String paramIsNull(List<Parameter> parameters, TypeClass retType, TypeClass msgType) {
        if (GenUtils.isEmpty(parameters)) {
            return null;
        }

        for (int i = 0; i < parameters.size(); i ++) {

        }

        return "";
    }
}
