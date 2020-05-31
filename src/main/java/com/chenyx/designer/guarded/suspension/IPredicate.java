package com.chenyx.designer.guarded.suspension;

/**
 * @desc 条件保护接口，用于返回指指定条件的不同状态
 * @author  chenyx
 * @date 2020-05-31
 * */
public interface IPredicate {

    /**
     * 方法接口
     * */
    public abstract  Boolean avaluate();
}
