package com.zyp.weixinsell.exception;


import com.zyp.weixinsell.enums.ExceptionEnum;

public class WechatException extends RuntimeException {
    private Integer code;

    public WechatException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public WechatException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
