package com.chenyx.designer.immutable.object;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc 路由管理器
 * @author chenyx
 * @date 2020-05-23
 * */
public final class MMSCRouter  {

    //用于保证多环境下可见性
    private  static volatile  MMSCRouter INSTANCE = new MMSCRouter();

    /**
     * @desc 号码和彩信中心信息对应关系
     * */
    private final Map<String,MMSCInfo> mmscInfoMap;

    public MMSCRouter() {
        this.mmscInfoMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    /**
     * @desc 加载号码和彩信中心信息对应关系的对应关系
     * @author chenyx
     * @date 2020-05-23
     *
     * */
    private static Map<String,MMSCInfo> retrieveRouteMapFromDB() {

        MMSCRouter mmscRouter = MMSCRouter.getInstance();
        Map<String,MMSCInfo> mmscMap ;
        //模拟逻辑
        if (mmscRouter == null) {
            mmscMap = new HashMap<>();
            MMSCInfo mmscInfo = new MMSCInfo("设备001", "http：//localhost/decive01");
            mmscMap.put("133123",mmscInfo);
        } else  {
            mmscMap = MMSCRouter.deepCopy(mmscRouter.mmscInfoMap);
            MMSCInfo mmscInfo = new MMSCInfo("设备002", "http：//localhost/decive02");
            mmscMap.put("133123",mmscInfo);
        }
        return  mmscMap;
    }

    /***
     * @desc 获取路由管理器实例
     * @author chenyx
     * @date 2020-05-23
     * */
    public static MMSCRouter getInstance() {
        return INSTANCE;
    }

    /***
     * @desc 设置路由管理器实例
     * @author chenyx
     * @date 2020-05-23
     * */
    public static void setInstance(MMSCRouter mmscRouter) {
        INSTANCE = mmscRouter;
    }


    public MMSCInfo getMMSCInfo(String key) {
        return mmscInfoMap.get(key);
    }

    /***
     * @desc 深度复制
     * @author chenyx
     * @date 2020-05-23
     * */
    private static Map<String,MMSCInfo> deepCopy( Map<String,MMSCInfo> infoMap) {
        Map<String,MMSCInfo> mmscMap= new HashMap<>();
        if (infoMap == null) {
            return infoMap;
        }
        for (String key : infoMap.keySet()) {
            mmscMap.put(key,new MMSCInfo(infoMap.get(key)));
        }
        return  mmscMap;
    }

    /***
     * @desc 获取路由映射信息，
     * @author chenyx
     * @date 2020-05-23
     * */
    public Map<String,MMSCInfo> getRouteMap() {
        //进行防御式复制
        return Collections.unmodifiableMap(deepCopy(mmscInfoMap));
    }

}
