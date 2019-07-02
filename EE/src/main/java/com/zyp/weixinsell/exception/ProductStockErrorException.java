package com.zyp.weixinsell.exception;


import com.zyp.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ProductStockErrorException extends RuntimeException {
    private Integer code;

    public ProductStockErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
