package com.simple.validate.match.impl;

import com.simple.validate.annotation.MaxLength;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class MaxLengthValidate implements AbstractValide<MaxLength> {

    @Override
    public void validate(MaxLength maxLength, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || value.toString().length() > maxLength.length()){
           int code = maxLength.code();
           String message = maxLength.message();
            throw new JsonRpc2Exception(code, fieldName + message);
        }
    }

}
