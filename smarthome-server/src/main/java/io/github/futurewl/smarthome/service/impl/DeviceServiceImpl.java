package io.github.futurewl.smarthome.service.impl;

import io.github.futurewl.smarthome.dataobject.Device;
import io.github.futurewl.smarthome.repository.DeviceRepository;
import io.github.futurewl.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午8:51
 * ==========================
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device save(Device device) {
        Device tempDevice = deviceRepository.findByDeviceName(device.getDeviceName());
        if (tempDevice != null) {
            device.setId(tempDevice.getId());
        }
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device exist(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName);
    }

}
