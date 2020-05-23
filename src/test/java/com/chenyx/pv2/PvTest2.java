package com.chenyx.pv2;

import com.chenyx.pv1.ConsumerThread;
import com.chenyx.pv1.ProducerThread;
import com.chenyx.stack.Stack;
import org.junit.Before;
import org.junit.Test;

/**
 * @desc 测试线程通信：一个生产者，一个消费者，生产者产生一个数据，消费者消费一值消费数据
 * @author chenyx
 * @date 2020-05-11
 * */
public class PvTest2 {

    private Stack<Integer> stack;

    @Before
    public void before() {
        this.stack = new Stack<>();
    }

    /**
     * @desc 测试线程通信：一个生产者，一个消费者，生产者产生一个数据，消费者消费一值消费数据
     * @author chenyx
     * @date 2020-05-11
     * */
    @Test
    public void testPV1()throws Exception {
        ConsumerThread1<Integer> consumerThread = new ConsumerThread1<>(this.stack);
        consumerThread.start();
        Thread.sleep(2000);
        ProducerThread1<Integer> producerThread = new ProducerThread1<>(this.stack,12);
        producerThread.start();
    }


    /**
     * @desc 测试线程通信：多个生产者，一个消费者，生产者产生一个数据，消费者消费一值消费数据
     * @author chenyx
     * @date 2020-05-11
     * */
    @Test
    public void testPV2() throws Exception {
        ConsumerThread1<Integer> consumerThread1 = new ConsumerThread1<>(this.stack);
        consumerThread1.start();
        //Thread.sleep(2000);
        for (int i =0 ; i < 10 ; i ++) {
            ProducerThread1<Integer> producerThread1 = new ProducerThread1<>(this.stack,i+1);
            producerThread1.start();
        }
        consumerThread1.join();
        System.out.println(".............testPV2 end .................");
    }

}
