package com.chenyx.designer.active.object;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class MapperFilter implements  MapperInterface {
    @Override
    public String toSay(String msg) {
        System.out.println("该方法已经被拦截");
        return msg;
    }


}
