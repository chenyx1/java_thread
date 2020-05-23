package com.chenyx.pv1;

import com.chenyx.stack.Stack;
import org.junit.Before;
import org.junit.Test;

/**
 * @desc 测试线程通信：一个生产者，一个消费者，生产者产生一个数据，消费者消费一个数据
 * @author chenyx
 * @date 2020-05-11
 * */

public class PVTest1 {

    private Stack<Integer> stack;

    @Before
    public void before() {
        this.stack = new Stack<>();
    }
    @Test
    public void testPV1() {
        ConsumerThread<Integer> consumerThread = new ConsumerThread<>(this.stack);
        consumerThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProducerThread<Integer> producerThread = new ProducerThread<>(this.stack,12);
        producerThread.start();
    }

    @Test
    public void testPV2()throws Exception{
        for (int i =0 ; i < 10 ; i++) {
            ConsumerThread<Integer> consumerThread = new ConsumerThread<>(this.stack);
            consumerThread.start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0; i < 10 ; i ++) {
            ProducerThread<Integer> producerThread = new ProducerThread<>(this.stack, i + 1);
            producerThread.start();
        }
      Thread.sleep(3000);
    }



}
