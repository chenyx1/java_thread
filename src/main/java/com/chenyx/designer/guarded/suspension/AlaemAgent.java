package com.chenyx.designer.guarded.suspension;


import java.security.SecureRandom;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @author chenyx
 * @desc 案例：连接服务器，负责将消息发送给服务器，
 * @date 2020-05-31
 */
public class AlaemAgent {

    private volatile boolean connectToserver = false;


    public AlaemAgent() {
        init();
    }

    /**
     * @desc 条件
     * @author chenyx
     * @date 2020-05-31
     */
    private final IPredicate predicate = new IPredicate() {
        @Override
        public Boolean avaluate() {
            return connectToserver;
        }
    };

    private final IBlocker blocker = new ConditionVarBlocker();

    private final Timer hearBeatTimew = new Timer(true);//心跳机制

    /**
     * @desc 初始化方法，连接服务器
     */
    public void init() {
        Thread connnectThread = new ConnectToServerThread();
        connnectThread.start();
        hearBeatTimew.schedule(new HeartBeatTask(),20000,5000);
    }

    public void onConnected() throws Exception {
        try {
            //连接服务器
            System.out.println("正在连接服务器。。。。。。。。。。。。。");
            //Thread.sleep(10000);
            System.out.println("连接服务器成功！");

            //唤起正在等待的操作
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectToserver = true;
                    return Boolean.TRUE;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @desc 发送消息
     * @author chenyx
     * @date 2020-05-31
     * */
    public String sendMsg(String msg) throws Exception{
        GuardAction<String> guardAction = new GuardAction<String>(predicate) {
            @Override
            public Object call() throws Exception {
                return doSendMsg(msg);
            }
        };
        return blocker.calwithGuard(guardAction);
    }


    /**
     * @desc 发送消息
     * @author chenyx
     * @date 2020-05-31
     * */
    public String doSendMsg(String msg) throws Exception{
        System.out.println("发送消:" + msg);
        System.out.println("发送消息成功！");
        return  "发送消息成功！";
    }


    /**
     * @desc 关闭连接
     * @author chenyx
     * @date 5050-05-31
     */
    public void disConnenct() {
        System.out.println("连接已关闭。。。。。。。。。");
        this.connectToserver = false;
    }

    public boolean testConnect() {
        boolean result = false;
        result = this.connectToserver;
        return result;
    }

    /**
     * @author chenyx
     * @desc 连接服务器线程
     * @date 2020-05-31
     */
    private class ConnectToServerThread extends Thread {
        @Override
        public void run() {
            try {
                onConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author chenyx
     * @desc 心跳检测，用于检测服务连接
     * @date 2020-05-31
     */
    private class HeartBeatTask extends TimerTask {

        @Override
        public void run() {

            try {
                System.out.println("正在检测连接状况。。。。。");
                if (!testConnect()) {
                    System.out.println("连接已经断开。。。。。");
                    disConnenct();
                    onConnected();
                }else {
                    System.out.println("连接已正常。。。。。");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
