package net.hyjuki.smgen.codegen;

import java.io.File;

public class CommonUtils {
    public static final String EXT_NAME_JAVA = ".java";
    public static final String EXT_NAME_XML = "Mapper.xml";
    public static final String DIR_SRC = "src";
    public static final String DIR_MAIN = "main";

    public static boolean strIsEmpty(String inputStr) {
        return (inputStr == null || inputStr.isEmpty());
    }

    public static String indent(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    public static String line() {
        return System.getProperty("line.separator");
    }

    public static String line(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i ++) {
            sb.append(line());
        }
        return sb.toString();
    }

    public static String lineAndIndent(int count) {
        return line() + indent(count);
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static String lowerCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    private static String getCamelCaseString(String inputStr,
                                            boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    public static String getProperty(String column) {
        return getCamelCaseString(column, false);
    }

    public static String renderProperty(String column) {
        return "#{" + CommonUtils.getProperty(column) + "}";
    }

    public static String getClassName(String tableName) {
        return getCamelCaseString(tableName, true);
    }

    public static String concatBySeparator(String separator, String... strings) {
        StringBuilder sb = new StringBuilder();
        if (strings.length == 1) {
            return sb.append(separator).append(strings[0]).toString();
        }

        int len = strings.length - 1;
        for (int i = 0; i < len; i++) {
            sb.append(strings[i]).append(separator);
        }
        return sb.append(strings[len]).toString();
    }

    public static String concatPackage(String... packages) {
        return concatBySeparator(".", packages);
    }

    public static String concatDir(String... dirs) {
        return concatBySeparator(File.separator, dirs);
    }
}
