package net.hyjuki.smgen.genconfig.base.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class CommonUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 通过时间加随机数的方式生成一个长度为20的数字字符串
     * @return
     */
    public static String getCode() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateStr = formatter.format(currentTime);

        return dateStr + String.valueOf(Math.random()).substring(2, 5);
    }

    /**
     * 生成文件名，使用年月做目录
     * @param ext 文件后缀名
     * @return 完整的目录文件名
     */
    public static String getFileName(String ext) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHHmmssSSS");
        String dateStr = formatter.format(currentTime);

        return dateStr + String.valueOf(Math.random()).substring(2, 5) + "." + ext;
    }

    /**
     * 生成一个指定长度的随机字符串
     * @param len 指定长度
     * @return
     */
    public static String strRandom(int len) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i ++) {
            int num = random.nextInt(62);
            sb.append(base.charAt(num));
        }

        return sb.toString();
    }
}
