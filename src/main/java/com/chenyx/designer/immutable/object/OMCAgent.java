package com.chenyx.designer.immutable.object;


/**
 * @desc  多线程进行操作
 *
 * */
public class OMCAgent extends Thread {

    private String  mmscKey;

    public OMCAgent(String mmscKey) {
        this.mmscKey = mmscKey;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MMSCInfo mmscInfo = MMSCRouter.getInstance().getMMSCInfo(mmscKey);
                System.out.println("mmscInfo :" + mmscInfo.toString());
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }
}
