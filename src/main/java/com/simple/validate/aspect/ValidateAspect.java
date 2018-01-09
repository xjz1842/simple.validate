package com.jiahui.validate.aspect;


import com.simple.validate.ValidatePool;
import com.simple.validate.annotation.BeanValidate;
import com.simple.validate.annotation.Validated;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Aspect
@Order(-1)
@Component
public class ValidateAspect {

    private Logger logger = LoggerFactory.getLogger(ValidateAspect.class);
//
    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Before("@annotation(validated)")
    public void before(JoinPoint joinPoint, Validated validated)throws Exception {

        Object[] arguments = joinPoint.getArgs();

        int k = 0;
        int length = arguments.length;

        while (k < length) {
            Object argument = arguments[k];

            //进行校验参数
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            String[] parameterName = getParameterNames(method);

            Annotation[] parameterAnnotation = method.getParameterAnnotations()[k];

            if(parameterAnnotation.length == 1 && parameterAnnotation[0].annotationType().equals(BeanValidate.class)){
                valideBean(argument,method.getParameterTypes()[k]);
            } else {
                valideField(parameterAnnotation,parameterName[k],argument);
            }
        }
        k++;
    }

    private void valideBean(Object target, Class<?> clazz)throws Exception{
        if(target == null){
          throw new RuntimeException("校验的对象为空");
        }

        //校验没一个字段
        for(Field field : clazz.getDeclaredFields()){
          Annotation[] annotations =  field.getAnnotations();
          PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
          Object value = pd.getReadMethod().invoke(target);
          valideField(annotations,field.getName(),value);
        }
    }

    private void valideField(Annotation[] parameterAnnotation,String fieldName,Object value){
        for (Annotation annotation : parameterAnnotation) {
            ValidatePool.validates.get(annotation.annotationType()).validate(annotation,fieldName,value);
        }
    }
    /**
     * 获取方法所有参数名
     *
     * @param method
     * @return
     */
    private String[] getParameterNames(Method method) {
        return parameterNameDiscoverer.getParameterNames(method);
    }

}
