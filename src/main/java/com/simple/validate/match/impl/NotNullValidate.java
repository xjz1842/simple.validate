package com.simple.validate.match.impl;

import com.simple.validate.annotation.NotNull;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;

public class NotNullValidate implements AbstractValide<NotNull> {

    @Override
    public void validate(NotNull notNull, String fieldName, Object value) {

        if (value == null) {
            int code = notNull.code();
            String message = notNull.message();
            throw new JsonRpc2Exception(code, fieldName + message);
        }
    }

}
