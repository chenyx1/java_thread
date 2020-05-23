package com.chenyx.designer.immutable.object;

public class UpdateMSCRouter extends Thread {

    @Override
    public void run() {
        super.run();
        MMSCRouter.setInstance(new MMSCRouter());
    }
}
