package com.zbkj.service.mpay.bean;


import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * @Author zqq
 */
public class MPayBase {

    public TreeMap<String, Object> getFieldMap() {
        TreeMap<String, Object> map = new TreeMap<>();
        Class cls = getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
