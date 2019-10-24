package com.gnnt.mybatisgenerator.jdk8;

/**
 * 测试1.8新特性
 */
public interface TeacheService {
    default void show(){
        System.out.println("TeacherService default method....");
    }
    public static void test(){
        System.out.println("TeacherService static method....");
    }
}
