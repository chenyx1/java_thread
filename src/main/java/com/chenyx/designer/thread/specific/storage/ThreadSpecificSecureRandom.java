package com.chenyx.designer.thread.specific.storage;

import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.security.SecureRandom;

/**
 * @desc thread specific storeage
 * @author  chenyx
 * @date 2020-05-24
 * */
public class ThreadSpecificSecureRandom {

    // 单例模式
    public static final ThreadSpecificSecureRandom INSTANCE =   new ThreadSpecificSecureRandom();

    private static final ThreadLocal<SecureRandom> SWCURE_RANDOM = new ThreadLocal<SecureRandom>(){

        @Override
        protected SecureRandom initialValue() {
            SecureRandom secureRandom ;
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (Exception e) {
                e.printStackTrace();
                secureRandom = new SecureRandom();
            }
            return secureRandom;
        }
    };


    private ThreadSpecificSecureRandom() {

    }

    /**
     * @desc  获取随机数
     * @author  chenyx
     * @date 2020-05-24
     * */
    public static int nextInt(int upperBound) {

        SecureRandom secureRandom = SWCURE_RANDOM.get();
        return secureRandom.nextInt(upperBound);
    }


    /**
     * @desc 删除无效对象，防止oom
     * @author  chenyx
     * @date 2020-05-24
     * */
    public static void remove() {
        SWCURE_RANDOM.remove();
    }

    public static  void setSeed(Long seed) {
        SecureRandom secureRandom = SWCURE_RANDOM.get();
        secureRandom.setSeed(seed);
    }
}
