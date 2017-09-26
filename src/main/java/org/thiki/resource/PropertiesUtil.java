package org.thiki.resource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by joeaniu on 10/19/16.
 */
public class PropertiesUtil {

    /**
     * put all fields in entity into the map. <br/>
     * assume the entity use java bean spec and use getXxx as the getter name when field's type is boolean.
     * @param map
     * @param entity
     */
    public static void copy(Map<String, Object> map, Object entity) {
        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field: fields) {
            String fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) continue;
            /** ignore inner class members */
            if (fieldName.startsWith("this$")) continue;

            Class type = field.getType();
            String getMethodName;
            if (boolean.class.equals(type) || (Boolean.class.equals(type))){
                if (fieldName.startsWith("is")){
                    getMethodName = fieldName;
                } else {
                    getMethodName = "is" + replaceFirstCharToUpper(fieldName);
                }

            }else{
                getMethodName = "get" + replaceFirstCharToUpper(fieldName);
            }

            Object fieldValue;
            try {
                Method getMethod = clazz.getMethod(getMethodName, null);
                fieldValue = getMethod.invoke(entity, null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }

            if (fieldValue == null) continue;

            map.put(fieldName, fieldValue);


        }
    }


    private static String replaceFirstCharToUpper(String fieldName) {
        char[] chars = new char[1];
        chars[0] = fieldName.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            fieldName = fieldName.replaceFirst(temp, temp.toUpperCase());
        }
        return fieldName;
    }
}
