package net.hyjuki.smgen.base.common;

public enum MsgData {
    SUCCESS(20000, new String[]{"操作成功"}),
    FAIL(1, new String[]{"操作失败"}),

    // 以下code是5位数
    ERROR_PARAM(10000, new String[]{"输入参数错误"}),
    ERROR_PARAM_NULL(10001, new String[]{"输入参数不能为空"}),
    ERROR_PARAM_ID_NULL(10002, new String[]{"输入ID为空"}),

    ERROR_RESULT_NULL(20001, new String[]{"没有查到相关信息"}),

    ERROR_NO_USER_LOGIN(50008, new String[]{"登录信息失效，请重新登录！"}),
    // 以上code是5位数

    // 以下code是4位数
    ERROR_NO_USER_AUTH(1000, new String[]{"未授权用户，无法操作该功能"}),
    ERROR_NO_USERNAME(1001, new String[]{"请输入登录帐号"}),
    ERROR_NO_PASSWORD(1002, new String[]{"请输入登录密码"}),
    ERROR_USER_LOCKED(1003, new String[]{"用户被锁定，请联系管理员"}),
    ERROR_USERNAME_PASSWORD(1004, new String[]{"用户名或者密码错误"}),
    ERROR_PERMISSION(1005, new String[]{"用户权限错误"}),
    ERROR_NO_ORG_INFO(1006, new String[]{"用户信息不完整，请联系管理员"}),
    ERROR_USERNAME_NULL(1007, new String[]{"用户名称不能为空"}),
    ERROR_OP_PERMISSION(1008, new String[]{"管理员只能操作自己机构下的权限"}),

    ERROR_USERNAME_DUPLICATED(1010, new String[]{"用户名称已存在"}),
    ERROR_USER_ACCOUNT_SAVE(1011, new String[]{"新增用户帐号错误"}),
    ERROR_ORGANIZATION_SAVE(1012, new String[]{"新增机构信息错误"}),
    ERROR_ORIG_PASSWORD(1013, new String[]{"输入原密码错误"}),
    ERROR_ACCOUNT_EXISTED(1014, new String[]{"该帐号已经存在"}),

    ERROR_WECHAT_CODE_NULL(3001, new String[]{"传入code不能为空"}),
    ERROR_WECHAT_OPENID(3002, new String[]{"接口错误，未查到该微信用户，请联系服务员。"}),
    ERROR_WECHAT_OPENID_NULL(3003, new String[]{"微信openid不能为空"}),
    ERROR_WECHAT_ILLEGAL(3004, new String[]{"非法操作，微信用户未授权"})
    ;

    private int code;
    private String[] message;
    private int type = 0;

    MsgData(int code, String[] message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageData {" + "code=" + code +
                ", message='" + message + '\'' + '}';
    }
}
