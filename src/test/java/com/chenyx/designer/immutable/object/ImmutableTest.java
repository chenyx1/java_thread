package com.chenyx.designer.immutable.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableTest {


    @Test
    public void testImmutable()throws Exception {

        OMCAgent omcAgent = new OMCAgent("133123");
        omcAgent.start();
        Thread.sleep(5000);
        UpdateMSCRouter updateMSCRouter = new UpdateMSCRouter();
        updateMSCRouter.start();
        omcAgent.join();
    }
}