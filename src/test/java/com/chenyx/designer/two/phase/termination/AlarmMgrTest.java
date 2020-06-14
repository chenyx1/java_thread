package com.chenyx.designer.two.phase.termination;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmMgrTest {

    @Test
    public void sendMsg() throws Exception {

        AlarmMgr alarmMgr = AlarmMgr.getInstance();
        alarmMgr.init();
        for (int i = 0 ; i < 10 ; i ++) {
            alarmMgr.sendMsg("msg" +i);
        }

        Thread.sleep(5000);
        alarmMgr.shurDown();
    }


    @Test
    public void sendMsg1() throws Exception {

        AlarmMgr alarmMgr = AlarmMgr.getInstance();
        alarmMgr.init();
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10000 ; i ++) {
                    try {
                        alarmMgr.sendMsg("msg" +i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        Thread.sleep(2000);
        alarmMgr.shurDown();
    }
}