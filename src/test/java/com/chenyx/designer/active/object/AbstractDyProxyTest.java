package com.chenyx.designer.active.object;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class AbstractDyProxyTest {

    @Test
    public void newInstance() throws Exception {
        MapperInterface mapperInterface =  DyProxy.newInstance(MapperInterface.class, new MapperFilter(), Executors.newCachedThreadPool());
       String mag = mapperInterface.toSay("hello");

        System.out.println("msg:" + mag);
    }
}