package io.github.futurewl.smarthome.service;

import io.github.futurewl.smarthome.dataobject.Device;

/**
 * 用户服务
 *
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午8:50
 * ==========================
 */
public interface DeviceService {
    Device save(Device device);

    Device update(Device device);

    Device exist(String deviceName);
}
