
# 默认支持的验证规则

MaxLengthValidate           验证最大长度
MaxValueValidate	        验证最大值	
MinLengthValidate        	验证最小长度	
MinValueValidate        	验证最小值	
NotNullValidate	            验证非空
RangeLengthValidate	        验证长度范围	
RangeValueValidate	        验证值范围	
RegexpValidate	            验证正则规则

Get started

# Maven依赖

```
    <groupId>com.validate</groupId>
    <artifactId>simple.validate</artifactId>
    <version>1.0.1-SNAPSHOT</version>
```

# 加入校验额配置

```
  新建一个类Config
  
  @Configuration
  public class Config{
  
    @Bean
      ValidateAspect validateAspect(){
          return new ValidateAspect();
      }
  }
```

# 验证JavaBean

```
@BeanValidate
class Bean{
    
    //非空验证
    @NotNull
    private String property1;
     
    //value的范围验证
    @RangeValue(min=2, max=120)
    private Integer property2;
 
    //集合的长度验证
    @MinLengthValidate(length=1)
    //list中的AnotherJavaBean对象会递归验证
    private List<AnotherJavaBean> list;
}

```

# 验证方法参数

1 在方法中加入验证规则注解

``` 
public void method(
            @NotNull,
            @MaxLength(length=1)
           String arg1,
           @NotNullValidate
           String arg2) {
    
 }
```

2 实现自定义验证规则

```
public class CustomerValidate extends AbstractValidate<CustomeRule>{
    @Override
    public void validate(CustomerValidate t,
            String fieldName,
            Object value)
            throws SmartValidateException {
             
        
    }
```

3 生效自定义验证规则
```
ValidatePool.addValidate(CustomeRule.class,new CustomerValidate());
```

