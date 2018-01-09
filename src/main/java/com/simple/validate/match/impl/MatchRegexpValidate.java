package com.simple.validate.match.impl;

import com.simple.validate.annotation.MatchRegexp;
import com.simple.validate.match.AbstractValide;
import com.yun.lib.rpc.base.JsonRpc2Exception;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class MatchRegexpValidate implements AbstractValide<MatchRegexp> {

    @Override
    public void validate(MatchRegexp matchRegexp, String fieldName, Object value) {

        if(StringUtils.isEmpty(value) || Pattern.matches(matchRegexp.pattern(), value.toString())){
            int code = matchRegexp.code();
            String message = matchRegexp.message();
            throw new JsonRpc2Exception(code, fieldName + message);
        }
    }
}
