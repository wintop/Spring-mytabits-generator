package net.hyjuki.smgen.genconfig.base.utils;

public class DataContants {
    public static final String DOT = ".";
    // 超级管理员角色
    public static final Integer SUPER_AMDIN_ROLE = 10000;
    // 组织机构管理员角色
    public static final Integer ORG_AMDIN_ROLE = 20000;
    // 平台机构
    public static final Long SUPER_ORG_ID = 1L;

    // 学科
    public static final Integer GROUP_SUBJECT = 10000;
    // 专业
    public static final Integer GROUP_MAJOR = 10001;

    // 普通文件
    public static final String BUCKET_FILE = "files";
    // 图片文件
    public static final String BUCKET_IMAGE = "images";
    // word文件
    public static final String BUCKET_DOCX = "docs";

    // Long类型的最大值
    public static final Long MAX_ID_LONG = Long.MAX_VALUE;

    // 无效(通用)
    public static final byte STATUS_INVALID = 0;
    // 有效(通用)
    public static final byte STATUS_VALID = 1;

    // token标签，和前端配合使用，必须和前端一致
    public static final String AUTHORIZATION = "H-TOKEN";

    // 数据类型
    public static final byte DATA_TYPE_STRING = 1;
    public static final byte DATA_TYPE_INTEGER = 2;
    public static final byte DATA_TYPE_LONG = 3;
    public static final byte DATA_TYPE_ARRAY = 9;
}
