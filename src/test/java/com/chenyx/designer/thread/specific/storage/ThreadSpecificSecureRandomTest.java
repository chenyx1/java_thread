package com.chenyx.designer.thread.specific.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadSpecificSecureRandomTest {


    @Test
    public void testThreadSpecificSecureRandom () throws InterruptedException {

        for (int i =0 ; i < 100000 ; i ++) {
            SpecificStorageClientThrad specificStorageClientThrad = new SpecificStorageClientThrad();
            specificStorageClientThrad.start();
        }
        Thread.sleep(10000);

    }
}