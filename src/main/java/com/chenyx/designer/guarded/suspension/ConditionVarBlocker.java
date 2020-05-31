package com.chenyx.designer.guarded.suspension;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc
 *
 *
 * */
public class ConditionVarBlocker implements IBlocker {

    private final Lock lock;
    private final Condition condition;
    private final boolean allowAccess2Lock;

    public ConditionVarBlocker() {
        this(false);
    }



    public ConditionVarBlocker(Lock lock) {
       this(lock,true);
    }


    public ConditionVarBlocker(boolean allowAccess2Lock) {
        this(new ReentrantLock(),allowAccess2Lock);
    }

    private ConditionVarBlocker(Lock lock, boolean allowAccess2Lock) {
        this.lock = lock;
        this.condition = this.lock.newCondition();
        this.allowAccess2Lock = allowAccess2Lock;
    }



    /**
     * @desc 条件不成立是，等待条件成立
     * @author chenyx
     * @date 2020-05-31
     *
     * */
    @Override
    public <T> T calwithGuard(GuardAction<T> guardAction) throws Exception {
        this.lock.lock();
        T result = null;
        try {
            IPredicate predicate = guardAction.getPredicate();
            System.out.println("condition:" + this.condition);
            System.out.println("the calwithGuard Thread :" + Thread.currentThread());
            while (!predicate.avaluate()) {
                System.out.println("waiting.............");
                this.condition.wait();
                System.out.println("服务被唤起。。。。。");
            }
            result = (T) guardAction.call();
        }catch (Exception e) {

        } finally {
            this.lock.unlock();
        }
        return result;
    }


    /**
     * @desc 条件成立时，唤起线程
     * @author chenyx
     * @date 2020-05-31
     *
     * */
    @Override
    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        this.lock.lock();
        try {
            if (stateOperation.call()) {
                System.out.println("the signalAfter Thread :" + Thread.currentThread());
                System.out.println("condition:" + this.condition);
                this.condition.signal();
            }
        }finally {
            this.lock.unlock();
        }
    }

    @Override
    public void signal() throws InterruptedException {
        this.lock.lock();
        try {
            this.condition.signal();
        }finally {
            this.lock.unlock();
        }
    }

    /**
     * @desc 条件成立时，唤起所有线程
     * @author chenyx
     * @date 2020-05-31
     *
     * */
    @Override
    public void broadcastAfter(Callable<Boolean> stateOprration) throws Exception {
        this.lock.lockInterruptibly();
        try {
            this.condition.signalAll();
        }finally {
            this.lock.unlock();
        }
    }


    /**
     * @desc 只能是重入锁才能获取
     * @author chenyx
     * @date 2020-05-31
     * */
    public Lock getLock() throws Exception {
        if (this.allowAccess2Lock) {
            return this.lock;
        }
        throw  new Exception("access to the lock disakkowed");
    }
}
