package com.dark.monitor.trans;

import java.lang.reflect.Method;

public class Trans2Entity {
    public static <T> T trans(Object obj, T t) throws InstantiationException, IllegalAccessException {

        /** 1，获取dto和entity方法*/
        Method[] srcMethods = obj.getClass().getMethods();
        Method[] targetMethods = t.getClass().getMethods();
        for (Method m : srcMethods) {
            String srcName = m.getName();
            /** 2，获取dto的get参数*/
            if (srcName.startsWith("get")) {

                try {
                    Object result = m.invoke(obj);
                    for (Method mm : targetMethods) {
                        String targetName = mm.getName();
                        /**3，获取对应entity的set*/
                        if (targetName.startsWith("set")
                                && targetName.substring(3, targetName.length()).equals(srcName.substring(3, srcName.length()))) {
                            mm.invoke(t, result);/**反射赋值*/
                        }
                    }
                } catch (Exception e) {
                    e.toString();
                }
            }
        }
        return t;
    }
}
