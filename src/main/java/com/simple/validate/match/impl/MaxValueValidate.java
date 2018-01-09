package com.simple.validate.match.impl;

import com.simple.validate.annotation.MaxValue;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class MaxValueValidate implements AbstractValide<MaxValue> {

    @Override
    public void validate(MaxValue maxValue, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || Integer.parseInt(value.toString()) > maxValue.value()){
            int code = maxValue.code();
            String message = maxValue.message();
            throw new JsonRpc2Exception(code, fieldName + message+value);
        }
    }
}
