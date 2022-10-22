package com.dark.monitor.trans;



import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

public class CachedBeanCopier {
    private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    public static void copy(Object source, Object target) {
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if(!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(source, target, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}