package com.chenyx.pv1;

import com.chenyx.stack.Stack;

/***
 * @desc 生产者
 * @author chenyx
 * @date 2020-05-10
 *
 * */
public class ProducerThread<T> extends Thread{
    private Stack<T> stack;

    private T t;
    public ProducerThread(Stack<T> stack, T t) {
        this.stack = stack;
        this.t = t;
    }

    public ProducerThread(Stack<T> stack) {
        this.stack = stack;

    }

    @Override
    public void run() {
        synchronized (this.stack) {
            while (this.stack.getSize() >= 1) {
                try {
                    System.out.println("producer the current Thread :" + Thread.currentThread() + ",size:" + this.stack.getSize());
                    this.stack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产
            this.stack.push(t);
            System.out.println("ProducerThread :" + t);
            this.stack.notifyAll();
        }
    }
}
