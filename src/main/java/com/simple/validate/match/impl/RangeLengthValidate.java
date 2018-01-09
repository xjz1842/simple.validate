package com.simple.validate.match.impl;

import com.simple.validate.annotation.RanageLength;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

public class RangeLengthValidate implements AbstractValide<RanageLength> {


    @Override
    public void validate(RanageLength ranageLength, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) ||  Integer.parseInt(value.toString()) > ranageLength.max() || Integer.parseInt(value.toString()) < ranageLength.min()){
            int code = ranageLength.code();
            String message = ranageLength.message();
            throw new JsonRpc2Exception(code, fieldName + String.format(message,ranageLength.max(),ranageLength.min()));
        }

    }
}
