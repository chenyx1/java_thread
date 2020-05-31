package com.chenyx.designer.guarded.suspension;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlaemAgentTest {



    @Test
    public void sendMsg() throws Exception{
        AlaemAgent alaemAgent = new AlaemAgent();
        //Thread.sleep(1000);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    alaemAgent.sendMsg("你好，");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.join();
    }
}