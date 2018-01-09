package com.simple.validate.match.impl;


import com.simple.validate.annotation.MinValue;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class MinValidate implements AbstractValide<MinValue> {

    @Override
    public void validate(MinValue minValue, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || Integer.parseInt(value.toString()) < minValue.value()){
            int code = minValue.code();
            String message = minValue.message();
            throw new JsonRpc2Exception(code, fieldName + message+minValue.value());
        }
    }
}
