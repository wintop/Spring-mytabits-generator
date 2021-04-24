package net.hyjuki.smgen.java;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.java.base.Parameter;
import net.hyjuki.smgen.java.base.TypeClass;

import java.util.List;

public class IfStatement {
    private String strIf = "if";

    public static String paramIsNull(List<Parameter> parameters, TypeClass retType, TypeClass msgType) {
        if (CommonUtils.isEmpty(parameters)) {
            return null;
        }

        for (int i = 0; i < parameters.size(); i ++) {

        }

        return "";
    }
}
