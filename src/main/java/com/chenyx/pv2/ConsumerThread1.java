package com.chenyx.pv2;

import com.chenyx.stack.Stack;

/**
 * @author chenyx
 * @desc 消费者
 * @date 2020-05-11
 */
public class ConsumerThread1<T> extends Thread {

    private Stack<T> stack;

    public ConsumerThread1(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
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
}
