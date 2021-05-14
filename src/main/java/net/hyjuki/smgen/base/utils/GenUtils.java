package net.hyjuki.smgen.base.utils;

import net.hyjuki.smgen.gencode.java.base.Element;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class GenUtils {
    public static final String EXT_NAME_JAVA = ".java";
    public static final String EXT_NAME_XML = "Mapper.xml";
    public static final String DIR_SRC = "src";
    public static final String DIR_WEB = "web";
    public static final String DIR_API = "api";
    public static final String DIR_VIEWS = "views";
    public static final String DIR_MAIN = "main";
    public static final String DIR_RESOURCES = "resources";
    public static final String DIR_CONFIG = "config";
    public static final String DIR_MAPPER = "mapper";
    public static final String SPACE = " ";
    public static final String SEMICOLON = ";";

    /**
     * 判断字符串是否空，如果是null或者""范围true
     * @param inputStr 需要判断的字符串
     * @return true或者false
     */
    public static boolean isEmpty(String inputStr) {
        return (inputStr == null || inputStr.isEmpty());
    }

    /**
     * 判断集合是否为空
     * @param collection
     * @return 如果集合为null或者集合的size()返回为0，为true，否则为null
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty() || collection.size() == 0);
    }

    /**
     * 以字符串格式输出指定个数的tab键
     * @param count
     * @return
     */
    public static String indent(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    /**
     * 以字符串格式输出一个换行
     * @return
     */
    public static String line() {
        return System.getProperty("line.separator");
    }

    /**
     * 以字符串格式，输出指定个数的换行
     * @param count
     * @return
     */
    public static String line(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i ++) {
            sb.append(line());
        }
        return sb.toString();
    }

    /**
     * 以字符串格式输入串一个换行和指定个数的tab
     * @param count
     * @return
     */
    public static String lineAndIndent(int count) {
        return line() + indent(count);
    }

    /**
     * 简单的将一个字符串的第一个字母大写
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 将字符串的第一个字母改成小写
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    /**
     * 将一个字符串转换为驼峰规则的字符串
     * 如：abc_def或者abc-def或者abc#def等字符串会被转换为 abcDef或者AbcDef
     * @param inputStr 要被转换的字符串
     * @param firstCharacterUppercase 第一个字符是否需要大写
     * @return
     */
    public static String getCamelCaseString(String inputStr,
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

    /**
     * 主要用于把表中的字段转换为Java类中的属性名，首字母小写
     * @param column
     * @return
     */
    public static String getProperty(String column) {
        return getCamelCaseString(column, false);
    }

    /**
     * 根据属性名称生成对应的get方法
     * @param name
     * @return
     */
    public static String genGetMethodName(String name) {
        return JavaConstants.NAME_GET + upperCase(name);
    }

    /**
     * 根据属性名生成对应的set方法
     * @param name
     * @return
     */
    public static String genSetMethodName(String name) {
        return JavaConstants.NAME_SET + upperCase(name);
    }

    /**
     * 根据属性名生成xml文件中的输入变量占位符
     * @param column
     * @return
     */
    public static String renderProperty(String column) {
        return "#{" + GenUtils.getProperty(column) + "}";
    }

    /**
     * 把数据库中的表名转换为类名，首字母大写
     * @param tableName
     * @return
     */
    public static String getClassName(String tableName) {
        return getCamelCaseString(tableName, true);
    }

    /**
     * 通过连接符连接字符串
     * @param separator
     * @param strings
     * @return
     */
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

    /**
     * 用"."连接字符串
     * @param strings
     * @return
     */
    public static String concatByDot(String... strings) {
        return concatBySeparator(".", strings);
    }

    /**
     * 用"."连接字符串，生成包名
     * @param packages
     * @return
     */
    public static String concatPackage(String... packages) {
        return concatBySeparator(".", packages);
    }

    /**
     * 生成目录名
     * @param dirs
     * @return
     */
    public static String concatDir(String... dirs) {
        for (int i = 0; i < dirs.length; i ++) {
            // 如果有'/'， 转换成系统的目录分隔符
            dirs[i] = dirs[i].replace('/', File.separatorChar);
            if (File.separator.equals(dirs[i].substring(dirs[i].length()-1))) {
                dirs[i] = dirs[i].substring(0, dirs[i].length()-1);
            }
        }
        return concatBySeparator(File.separator, dirs);
    }

    /**
     * 把Java类的包名转换为目录
     * @param pkgName
     * @return
     */
    public static String pkgToDir(String pkgName) {
        return pkgName.replace('.', File.separatorChar);
    }

    /**
     * 把List中的内容转换为字符串，并在每个元素的前面添加指定数量的tab键
     * @param list
     * @param indent
     * @param <T>
     * @return
     */
    public static <T extends Element> String formatList(List<T> list, int indent) {
        StringBuilder sb = new StringBuilder();

        if (list == null || list.size() == 0) {
            return "";
        }

        for (Element element: list) {
            if (indent > 0) {
                sb.append(indent(indent));
            }

            sb.append(element.formatString());
            sb.append(line());
        }

        return sb.toString();
    }

    /**
     * 把List中的内容转换为字符串
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Element> String formatList(List<T> list) {
        return formatList(list, 0);
    }
}
