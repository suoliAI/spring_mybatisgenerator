package com.gnnt.mybatisgenerator.model;

import com.gnnt.mybatisgenerator.model.proxy.Proxy;
import com.gnnt.mybatisgenerator.model.strategy.ConcreteStrategyA;
import com.gnnt.mybatisgenerator.model.strategy.ConcreteStrategyB;
import com.gnnt.mybatisgenerator.model.strategy.Context;
import org.junit.jupiter.api.Test;

public class ModelTest {
    @Test
    public void test_RealizeType() throws CloneNotSupportedException {
        Realizetype realizetype = new Realizetype();
        Realizetype clone = (Realizetype) realizetype.clone();
        System.out.println("realizetype和clone是否是一个对象"+(realizetype==clone));
    }
    @Test
    public void test_Strategy(){
        Context context = new Context();
        ConcreteStrategyA concreteStrategyA = new ConcreteStrategyA();
        context.setStrategy(concreteStrategyA);
        context.strategyMethod();
        ConcreteStrategyB concreteStrategyB = new ConcreteStrategyB();
        context.setStrategy(concreteStrategyB);
        context.strategyMethod();

    }
    @Test
    public void test_proxy(){
        Proxy proxy=new Proxy();
        proxy.Request();
    }
}
