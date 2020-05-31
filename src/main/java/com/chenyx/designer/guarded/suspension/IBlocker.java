package com.chenyx.designer.guarded.suspension;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.Callable;

/**
 *
 *
 * */
public interface IBlocker {

   public abstract  <T> T calwithGuard(GuardAction<T> guardAction) throws Exception;

   public abstract void signalAfter(Callable<Boolean> stateOperation) throws Exception;

   void signal()throws InterruptedException;

   void broadcastAfter(Callable<Boolean> stateOprration) throws Exception;
}
