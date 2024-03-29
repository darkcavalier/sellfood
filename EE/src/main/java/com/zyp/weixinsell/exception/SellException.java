package com.zyp.weixinsell.exception;


import com.zyp.weixinsell.enums.ExceptionEnum;


public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ExceptionEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
