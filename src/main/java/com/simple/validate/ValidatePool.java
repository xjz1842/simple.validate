package com.simple.validate;

import com.simple.validate.annotation.*;
import com.simple.validate.match.AbstractValide;
import com.simple.validate.match.impl.NotNullValidate;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class ValidatePool {

    public static final Map<Class<? extends Annotation>, AbstractValide> validates = new HashMap<>();

    static{
        validates.put(NotNull.class, new NotNullValidate());
        validates.put(MatchRegexp.class, new NotNullValidate());
        validates.put(MaxLength.class, new NotNullValidate());
        validates.put(MinLength.class, new NotNullValidate());
        validates.put(MaxValue.class, new NotNullValidate());
        validates.put(MinValue.class, new NotNullValidate());
        validates.put(RanageValue.class, new NotNullValidate());
        validates.put(RanageLength.class, new NotNullValidate());
    }

    public static void addValidate(Class<? extends Annotation> annotation, AbstractValide validate){
        validates.put(annotation,validate);
    }
}

