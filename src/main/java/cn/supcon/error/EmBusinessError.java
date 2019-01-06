package cn.supcon.error;

public enum EmBusinessError implements CommonError {
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法！"),
    UNKNOWN_ERROR(10002, "未知错误！"),
    // 2开头的是用户相关的错误
    USER_NOT_EXIST(20001, "用户不存在！"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码错误！"),
    ;



    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
