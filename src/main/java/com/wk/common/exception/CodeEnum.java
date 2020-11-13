package com.wk.common.exception;

public enum CodeEnum {

    SUCCESS(0, "请求成功"),
    PARAM_ERROR(300, "参数有误"),
    SQL_ERR(301, "SQL执行出错"),
    DB_ERR(302, "数据库连接超时"),
    COLUMN_ERR(400, "参数有误, 数据库列不存在"),
    FAIL(500, "服务器异常"),
    HAS_DATA_ERR(501, "已有数据,无法删除"),
    VISIBLE_ERR(502, "该属性尚无数据,无法设置为可见"),
    NAME_UPDATE_ERR(503, "该事件已有数据,无法修改事件名"),
    ID_NOT_EXIST_ERR(504, "ID不存在"),
    NAME_EXIST_ERR(506, "已存在"),
    INPUT_FORMAT_ERR(507, "仅输入支持英文"),
    BLACK_WORD_ERR(508, "无法使用系统保留字符串, 请重新输入"),
    PARAM_NOT_EXIST_ERR(509, "表字段不存在"),
    ;

    private Integer code;

    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeEnum parse(int code) {
        CodeEnum[] values = values();
        for (CodeEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("Unknown code of ResultEnum");
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


