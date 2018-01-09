package com.simple.validate.match;

import java.lang.annotation.Annotation;

public interface AbstractValide<T extends Annotation> {

    void validate(T t,String fieldName,Object value);
}
