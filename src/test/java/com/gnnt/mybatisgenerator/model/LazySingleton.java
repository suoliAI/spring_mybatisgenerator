package com.gnnt.mybatisgenerator.model;

/**
 * 懒汉式单例模式
 * 多线程中不要删除volatile和synchronized关键字 否则会存在线程非安全问题
 */
public class LazySingleton {
    private static volatile LazySingleton instance = null; // 保证instance在所有线程中同步
    private LazySingleton(){ //私有化构造器，防止在外部被实例化

    }
    public static synchronized LazySingleton getInstance(){ //
        if(instance==null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
