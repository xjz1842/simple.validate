package com.simple.validate.match.impl;


import com.simple.validate.annotation.RanageValue;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class RangeValueValidate  implements AbstractValide<RanageValue> {

    @Override
    public void validate(RanageValue ranageValue, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || Integer.parseInt(value.toString()) > ranageValue.max() || Integer.parseInt(value.toString()) < ranageValue.min()){
            int code = ranageValue.code();
            String message = ranageValue.message();
            throw new JsonRpc2Exception(code, fieldName + String.format(message,ranageValue.max(),ranageValue.min()));
        }
    }
}
