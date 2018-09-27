package sorm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装常用反射的操作
 */
public class ReflectUtils {
    public static Object invokeGet(String fieldName,Object obj){
        /**
         * 通过反射机制，调用属性对应的get方法，得到主键
         */
        try {
            Class clazz=obj.getClass();
            Method method=clazz.getMethod("get"+ StringUtils.firstChar2UpperCase(fieldName),null);
            return method.invoke(obj,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
