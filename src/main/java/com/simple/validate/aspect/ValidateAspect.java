package com.simple.validate.aspect;

import com.simple.validate.ValidatePool;
import com.simple.validate.annotation.BeanValidate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
public class ValidateAspect {

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.simple.validate.annotation.Validated)")
    public void validateAspect(){}

    @Around("validateAspect()")
    public Object checkIsValid(ProceedingJoinPoint joinPoint)throws Throwable,Exception {
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
            k++;
        }

        Object object = joinPoint.proceed();
        return object;
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
