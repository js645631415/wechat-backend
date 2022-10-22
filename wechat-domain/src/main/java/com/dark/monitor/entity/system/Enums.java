package com.dark.monitor.entity.system;

public interface Enums<T> {
    T getCode();

    default String getLiteral() {return null;}
}
