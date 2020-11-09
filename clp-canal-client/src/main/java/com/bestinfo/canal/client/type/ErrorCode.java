package com.bestinfo.canal.client.type;

public enum ErrorCode {
    SUCCESS(0, "成功"),

    ServiceExecError(3020, "service error"),
    ORDER_DEALING(5555, "交易处理中, 请稍后查询！"),    // 终端特殊错误码
    ORDER_UNDO_CLP_DEALING(5556, "向中彩请求彩票注销,响应超时,注销状态未知, 请稍后查询！"),    // 终端特殊错误码
    ORDER_ALREADY_PROCESSED(6003, "交易订单之前已处理完"),

    //clp-canal-client 30000-30999
    TIMEOUT(30001, "超时"),
    SIGNERROR(30002, "验签异常"),
    HTTPFAIL(30003, "请求失败"),
    EXCEPTION(30004, "系统异常"),
    DOWNLOADERR(30005, "下载失败"),
    READTIMEOUT(30006, "读超时"),
    LOCK_DEAL_FAIL(30009, "交易锁失败"),
    CASH_QUERY_FAILED(30010, "兑奖查询失败！"),
    CASH_FAILED(30011, "兑奖失败！"),

    //clp-canal-auth 31000-31999
    CACHE_WITHOUT_GAME_DRAW_INFO(31001, "系统缓存没有对应游戏期信息"),

    //clp-canal-trans 32000-32999
    GET_SELLING_GAME_ISNULL(32000, "get selling game is null"),
    PARAM_IS_NULL(32001, "请求参数为空"),
    NOT_WIN(32002, "未中奖"),
    ALREADY_CASHED(32003, "该票已兑奖"),
    CLP_TICKET_INFO_NOT_EXIST(32004, "联销彩票信息不存在"),
    CUR_DRAW_NOT_EXIST(32005, "当前期不存在"),
    CASH_FEE_RATE_NOT_EXIST(32006, "投注机兑奖代销费比例不存在"),
    TERMINAL_TYPE_ERROR(32007, "获取投注机类型错误"),
    CANNOT_CLASS_CASH(32008, "系统不支持奖级兑奖"),
    STAKE_OR_PRIZE_ERROR(32009, "中奖注数或者中奖总金额错误"),
    OVER_TMN_CASH_LIMIT(32010, "超出投注机兑奖限额"),
    ORDINARY_TMN_CANNOT_CASH_TAX(32011, "普通投注机不兑有税奖"),
    ACCOUNT_NOT_EXIST(32012, "账户信息不存在"),
    UPDATE_ACCOUNT_ERROR(32013, "更新账户信息失败"),
    ACCOUNT_IS_UNUSED(32014, "账户不可用"),
    UPDATE_TICKET_PRIZE_ERROR(32015, "更新中奖数据失败"),
    MACHINE_UPDATE_ACCOUNT_ERROR(32016, "兑奖机更新中奖数据失败"),
    NOT_SUPPORT_TERMINAL_TYPE(32017, "投注机类型不支持"),
    ALREADY_CASHED_OTHER_STATION(32018, "请到other站点进行兑奖确认"),
    CLP_CASH_FAILED(32019, "中彩兑奖失败"),
    PARAM_ERROR(32020, "请求参数错误"),
    CASH_TIME_PASSED(32021,"兑奖时间已过"),
    CASH_DRAW_PASSED(32022,"兑奖期已过"),
    CASH_DRAW_NOT_DUE(32023,"兑奖期未到"),
    CUR_DRAW_INVALID(32024,"没有有效的当前期"),
    CASH_DRAW_NOT_EXIST(32025,"兑奖期次不存在"),
    ALREADY_CASHED_CENTER(32026, "已在中心兑奖"),
    ALREADY_FORGOT_PRIZE(32027, "已弃奖"),

    CASH_PLATFORM_NOT_SUPPORT(32029,"当前不支持代理方平台"),

    GET_CLP_PLAY_NULL(32102, "获取对应玩法失败"),

    QUERY_BET_CLP_ORDER_ISNULL(32103, "同密码的投注订单不存在"),
    QUERY_BET_CLP_ORDER_TMN_ERR(32104, "同密码的投注订单不存在"),
    QUERY_BET_CLP_ORDER_FAIL(32200, "投注失败"),

    CLP_BET_FAIL(32300, "向中彩投注失败"),

    CLP_TICKET_UNDO_NONSUPPORT(32331, "彩票注销参数不支持"),
    CLP_TICKET_INFO_ISNULL(32333, "彩票不存在"),
    CLP_TICKET_UNDO_FAIL(32334, "彩票注销失败"),
    CLP_TICKET_REVERSE_UNDO(32335, "此票已冲正注销"),
    CLP_TICKET_UNDO_UNKNOWN_STATUS(32336, "查询注销票未知状态票"),

    REVERSE_NOT_ALLOW_DRAW_NOT_END(32340, "本系统游戏期销售未截止,不允许冲正"),

    ORDER_CONSISTENT_UNFINISHED(32350, "游戏期交易中订单处理未完成"),

    Canal_CLP_Exception(32999, "系统异常"),
    //EB 查询结果为空
    EBCLP_QUERY_EMPTY(150,"查询结果为空"),
    //EB 上报有错误
    EBCLP_UPLOAD_ERROR(50,"上报有错误"),
    FILE_NOT_EXIST(33000, "文件不存在"),
    DATA_FILE_NOT_EXIST(33001, "数据文件不存在"),
    SIGN_FILE_NOT_EXIST(33002, "签名文件不存在"),
    WIN_DATA_INSERT_DB_FAIL(33003, "中奖数据入库失败"),

    //中彩响应码
    CLP_OPERATION_FAIL(-1, "操作失败"),
    CLP_SYSTEM_BUSY(-2, "系统忙"),
    CLP_COMM_FAIL(-3, "通信失败"),
    CLP_COMM_TIMEOUT(-4, "通信超时"),
    CLP_COMM_INTERRUPT(-5, "通信中断，请重新联接后再试"),
    CLP_COMM_FAILURE(-9999, "通信故障，请稍后再试！"),

    CLP_TICKET_BET_FAIL_1016(-1016, "投注不成功，不计入销售额"),
    CLP_TICKET_BET_FAIL_1126(-1126, "没有指定彩票的销售信息"),
    CLP_TICKET_BET_FAIL_1276(-1276, "销售票不存在"),

    CLP_TICKET_ALREADY_UNDO(-1270, "此票已注销，不允许重复注销"),
    CLP_TICKET_ALREADY_CENTER_UNDO(-1271, "此票已中心注销，不能重复注销"),
    CLP_TICKET_ALREADY_REVERSE(-1272, "此票已冲正"),
    CLP_REVERSE_DRAW_OVER(-2999, "此票已过期,冲正失败"),

    CIP_ALREADY_CASHED(-1108, "该票已经兑过奖了。不能重复兑奖"),

    CLP_TOKEN_AUTH_FAIL(2004, "渠道token认证失败"),
    CLP_TOKEN_AUTH_ILLEGAL(2005, "渠道token不合法"),

    CLP_GAME_ENGINE_NOT_GET_DATA(4005, "游戏引擎没有获取到数据");

    private int code;
    private String msg;

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (ErrorCode errorCode : ErrorCode.values())
            if (errorCode.getCode() == code)
                return errorCode.getMsg();
        return null;
    }

    public static boolean isClpCodeBetFail(int code) {
        if (1001 <= code && code <= 1007) {
            return true;
        } else if (2001 <= code && code <= 2020) {
            return true;
        } else if (3001 <= code && code <= 3025) {
            return true;
        } else if (-2000 < code && code <= -1000 ) {
            return true;
        } else if (-2310 == code) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isClpCodeUnknown(int respCode) {
        if (ErrorCode.CLP_OPERATION_FAIL.getCode() == respCode ||
                ErrorCode.CLP_SYSTEM_BUSY.getCode() == respCode ||
                ErrorCode.CLP_COMM_FAIL.getCode() == respCode ||
                ErrorCode.CLP_COMM_TIMEOUT.getCode() == respCode ||
                ErrorCode.CLP_COMM_INTERRUPT.getCode() == respCode ||
                ErrorCode.CLP_COMM_FAILURE.getCode() == respCode) {
            return true;
        } else {
            return false;
        }
    }
}
