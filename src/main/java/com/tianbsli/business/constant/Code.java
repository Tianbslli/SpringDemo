package com.tianbsli.business.constant;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 5:04 下午
 *
 * baseResult返回值的枚举
 */
public enum Code {

    UNDEFINED(-1, "未定义"),
    SUCCESS(0, "成功"),
    PARAM_ERROR(101, "参数异常");

    private int code;
    private String msg;

    // 构造方法
    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }


    public static String getMsgByCodeInt(int codeInt) {
        for (Code e : Code.values()) {
            if (e.getCode() == codeInt) {
                return e.msg;
            }
        }
        throw new IllegalArgumentException("未定义的code码:" + codeInt);
    }

    public static Code getCodeByCodeInt(int codeInt) {
        for (Code code : Code.values()) {
            if (code.getCode() == codeInt) {
                return code;
            }
        }
        throw new IllegalArgumentException("未定义的code码:" + codeInt);
    }
}
