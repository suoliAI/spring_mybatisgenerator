package com.gnnt.mybatisgenerator.model;

/**
 * 原型模式
 * 原型模式的克隆分为浅克隆和深克隆，Java 中的 Object 类提供了浅克隆的 clone() 方法
 * 具体原型类只要实现 Cloneable 接口就可实现对象的浅克隆，这里的 Cloneable 接口就是抽象原型类。
 *
 * 原型（Prototype）模式的定义如下：用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。
 * 在这里，原型实例指定了要创建的对象的种类。用这种方式创建对象非常高效，根本无须知道对象创建的细节。
 */
//具体原型类
public class Realizetype  implements Cloneable{
    public Realizetype(){
        System.out.println("具体原型类已经创建成功...");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型类拷贝成功...");
        return super.clone();
    }
}
