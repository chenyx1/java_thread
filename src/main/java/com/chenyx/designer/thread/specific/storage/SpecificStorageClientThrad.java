package com.chenyx.designer.thread.specific.storage;

public class SpecificStorageClientThrad  extends Thread{

    @Override
    public void run() {
        try {
            System.out.println("thread:" +  ThreadSpecificSecureRandom.nextInt(4) );
        }catch (Exception e) {

        }finally {
            ThreadSpecificSecureRandom.remove();
        }

    }
}
