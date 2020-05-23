package com.chenyx.designer.immutable.object;


/**
 * @desc 彩信中心的路由信息
 *
 * @author chenyx
 * @date 2020-05-23
 * */
public  final class MMSCInfo{

    private final String  DEVICE_ID;

    private final String URL;

    public MMSCInfo(String DEVICE_ID, String URL) {
        this.DEVICE_ID = DEVICE_ID;
        this.URL = URL;
    }

    public MMSCInfo(MMSCInfo mmscInfo) {
        this.DEVICE_ID  = mmscInfo.DEVICE_ID;
        this.URL = mmscInfo.URL;
    }

    public String getDEVICE_ID() {
        return DEVICE_ID;
    }

    public String getURL() {
        return URL;
    }

    @Override
    public String toString() {
        return "MMSCInfo{" +
                "DEVICE_ID='" + DEVICE_ID + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
