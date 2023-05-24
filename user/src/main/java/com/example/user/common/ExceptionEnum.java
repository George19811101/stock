package com.example.user.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    /**
     * 异常 用户模块异常code从1000开始
     */


    SMS_SEND_FAIL(1004, "短信发送失败"),

    PHONE_NO_WRONG_FORMAT(1005, "手机号格式有误"),

    MAIL_WRONG_FORMAT(1006, "邮箱格式有误"),

    CAPTCHA_NEW_ERROR(10071, "验证码有误"),

    CAPTCHA_OLD_ERROR(10072, "旧验证码有误"),

    CAPTCHA_ERROR(1007, "验证码有误"),

    THE_TWO_PASSWORDS_DO_NOT_MATCH(1008, "两次密码不一致"),

    AREA_CODE_NOT_NULL(1009, "区号不能为空"),

    PHONE_NO_EXIST(1010, "手机号已存在"),

    MAIL_EXIST(1011, "邮箱已存在"),

    ADD_FAIL(1012, "添加失败"),

    UPDATE_FAIL(1013, "更新失败"),

    PLEASE_SEND_CAPTCHA(1014, "请发送验证码"),

    PHONE_NO_NOT_EXIST(1015, "手机号未注册"),

    PHONE_DIFF_STORED_DATA(10151, "手机号与所存储的用户记录不符"),

    MAIL_NOT_EXIST(1016, "邮箱未注册"),
    MAIL_DIFF_STORED_DATA(10152, "邮箱号码与所存储的用户记录不符"),
    PASSWORD_ERROR(1017, "密码错误"),
    PASSWORD_ISNULL(2017, "密码为空"),
    NEWPASSWORD_ISNULL(2018, "新密码为空"),
    NEWPASSWORD_ISNOTSAME(2018, "两个新密码不一致"),

    ID_CARD_FORMAT_ERROR(1018, "身份证号码格式有误"),

    DELETE_FAIL(1019, "删除失败"),

    ID_CARD_NOT_CERTIFICATION(1020, "用户身份还未认证"),

    PASSWORD_DOES_NOT_MATCH(1021, "两次密码不一致"),

    NEW_PASSWORD_DOES_NOT_MATCH_OLD_PASSWORD(1022, "新密码不能与旧密码相同"),

    OLD_PASSWORD_ERROR(1023, "原密码错误"),

    DATA_TYPE_ERROR(1024, "该注解只作用于BigDecimal数据类型"),

    BANK_ACCOUNT_NOT_NULL(1025, "银行卡号不能为空"),

    PAY_TYPE_ERROR(1026, "支付类型有误"),

    QR_CODE_NOT_NULL(1027, "收款码不能为空"),

    TWICE_PASSWORD__MATCH(1028, "与上次密码不能一致"),

    BIG_DECIMAL_PARAM_NOT_ZERO(1029, "参数不能为0"),

    BIG_DECIMAL_DIVIDEND_NOT_ZERO(1030, "被除数不能为0"),

    BIG_DECIMAL_PARAM_LOST(1031, "参数丢失"),

    INSUFFICIENT_BALANCE(1032, "余额不足"),

    TRADE_PASSWORD_EXIST(1033, "交易密码已存在"),

    ID_CARD_IMAGE_ERROR(1034, "身份证正反面都需要上传"),

    CERTIFICATION_PICTURES(1035, "认证图片不能为空"),

    REPEAT_CERTIFICATION(1036, "请勿重复认证"),

    REMOTE_CALL_EXCEPTION(1037, "远程调用异常"),

    CACHE_EXCEPTION(1038, "缓存异常"),

    WALLET_ADDRESS_ERROR(1039, "地址有误"),

    TOP_UP_SUCCESSFUL(1040,"已充值成功"),

    SECRET_OVERDUE(1041,"秘钥已过期"),

    TIME_AREA_ERROR(1042, "时间区间有误"),

    START_TIME_IS_NOT_NULL(1043, "开始时间不能为空"),

    END_TIME_IS_NOT_NULL(1044, "结束时间不能为空"),

    START_TIME_CANNOT_BE_GREATER_THAN_END_TIME(1045, "开始时间不能大于结束时间"),

    UNBOUNDED_GOOGLE_AUTHENTICATOR(1046, "未绑定谷歌认证"),

    NO_USER_BOUND_DEVICE(1047, "该设备未绑定您"),

    LOGIN_DEVICE_ERROR(1048, "登录设备异常，请发送手机验证码"),

    DEVICE_Not_Null(1049,"设备唯一ID不能为空"),

    SMS_VERIFICATION_CODE_ERROR(1050,"发送短信验证码出现异常"),

    RESULT_SMS_VERIFICATION_CODE_ERROR(1051,"发送短信验证码返回结果异常"),

    CHECK_SMS_CODE_ERROR(1052,"校验短信验证码出现异常"),

    CHECK_RESULT_SMS_CODE_ERROR(1053,"校验短信验证码返回结果异常"),

    NOT_IN_JSON_FORMAT(1054, "请求参数必须是json格式"),

    PARAMETER_FORMAT_ERROR(1054, "请求的参数格式错误"),

    REQUEST_DATA_ERROR(1055, "当前只支持文本消息，不支持二进制消息"),

    NOT_CONNECTED(1056, "尚未握手成功，无法向客户端发送WebSocket消息"),

    NOT_LOGIN(1057, "未登录"),

    METHOD_CODE_ERROR(1058, "method code有误"),

    CASH_OUT_ERROR(1059, "提现失败"),

    CASH_OUT_HIGHER_ERROR(1060, "超过可提上限"),

    CASH_TO_FLOOR_ERROR(1061, "低于可提下限"),

    NO_CASH(1062, "不支持提币"),

    LOGIN_EXPIRED(1063, "登录过期"),

    TRANSFER_STATUS_ERROR(1064, "转账状态有误"),

    TRANSFER_ERROR(1067, "转账失败"),

    TRADE_PASSWORD_ERROR(1069, "请设置交易密码"),

    TRADE_PASSWORD_ERRORS(1070, "交易密码错误"),

    GOOGLE_CODE_ERROR(1071,"谷歌验证码有误"),

    ADDRESS_ERROR(1072, "用户钱包地址异常"),

    INVITATION_NULL(1073, "邀请码无效"),

    BATCH_ACQUISITION_WALLET_ERROR(1074, "批量获取钱包地址异常"),

    NOT_AN_AGENT(1075, "您还不是代理商!"),

    UNBOUND(1084, "未绑定!"),

    PHONE_NO_OR_MAIL_WRONG_FORMAT(1076,"手机或者邮箱格式错误"),

    TYPE_ERROR(1077,"类型错误!!!"),

    MODIFY_PHONE_ERROR(1078,"手机号相同"),

    MODIFY_MAIL_ERROR(1079,"邮箱号相同"),

    Binding_PHONE(1080,"已绑定手机号"),

    Binding_MAIL(1081,"已绑定邮箱号"),

    USER_TRANSACTION_ERROR(1082,"24小时内您已禁止资金交易"),

    TWO_USERS_THE_SAMEID(1083, "转帐的接收方与发送方同一人"),

    NO_SMS(1084,"暂不支持手机注册！"),

    NO_USER(1085,"查询不到用户！"),

    NO_TOKEN(10850,"header中没有带入TOKEN！"),
     TOKEN_ERROR(10851,"token不正确，请不要通过非法手段创建token"),

    NO_ENOUGHT_MONEY(2000,"用户钱包余额不足，不足够支付！"),
    SCHEDULE_IS_START(2001,"日配置已在支行，不能够撤销！"),
    SYSTEM_EXCEPTION(1999, "系统异常");

    private int code;

    private String msg;

}
