package com.simple.validate.match.impl;

import com.simple.validate.annotation.MinLength;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class MinLengthValidate implements AbstractValide<MinLength> {

    @Override
    public void validate(MinLength minLength, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || value.toString().length() < minLength.length()){
            int code = minLength.code();
            String message = minLength.message();
            throw new JsonRpc2Exception(code, fieldName + message+value);
        }
    }
}
