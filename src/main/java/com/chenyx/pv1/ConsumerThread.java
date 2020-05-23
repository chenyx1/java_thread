package com.chenyx.pv1;

import com.chenyx.stack.Stack;

/**
 * @desc 消费者
 * @author chenyx
 * @date 2020-05-11
 * */
public class ConsumerThread<T> extends Thread{

    private Stack<T> stack;

    public ConsumerThread(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        synchronized (this.stack) {
            while (this.stack.getSize() <= 0) {
                System.out.println("consumer the current Thread :" + Thread.currentThread() + ",size:" + this.stack.getSize());
                try {
                    this.stack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" consumer the current Thread :" + Thread.currentThread() + ",size:" + this.stack.getSize());
            T t = this.stack.pop();
            System.out.println("consumer the current Thread :" + Thread.currentThread() + ",value:" + t);
            this.stack.notifyAll();
        }

    }
}
