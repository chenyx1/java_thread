package com.chenyx.designer.guarded.suspension;

import java.util.concurrent.Callable;

/**
 * @desc 抽象目标动作
 * */
public abstract class GuardAction<T> implements Callable {

    private final IPredicate predicate;


    public GuardAction(IPredicate predicate) {
        this.predicate = predicate;
    }

    public IPredicate getPredicate() {
        return predicate;
    }
}
