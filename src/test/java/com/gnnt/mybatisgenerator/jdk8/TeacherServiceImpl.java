package com.gnnt.mybatisgenerator.jdk8;

public class TeacherServiceImpl implements TeacheService , TeacherService2{
    @Override
    public void show() {
//        TeacheService.super.show();
        TeacherService2.super.show();
    }
//    @Override
//    public void show() {
//        System.out.println("TeacherServiceImpl default method....");
//    }
}
