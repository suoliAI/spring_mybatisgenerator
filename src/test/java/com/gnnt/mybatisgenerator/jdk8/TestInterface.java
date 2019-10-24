package com.gnnt.mybatisgenerator.jdk8;

import org.junit.jupiter.api.Test;

public class TestInterface {
    @Test
    public void test(){
        TeacheService teacheService = new TeacherServiceImpl();
        teacheService.show();
        JDK8Test jdk8Test = new JDK8Test();
    }
}
