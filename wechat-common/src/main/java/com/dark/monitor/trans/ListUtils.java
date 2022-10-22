package com.dark.monitor.trans;

public class ListUtils {
    public static String list2In(Object list){
        return list.toString().replace("[","").replace("]","");
    }
}
