package com.zyp.weixinsell.exception;


import com.zyp.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = code;
    }

    public OrderException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
