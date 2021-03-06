package io.github.futurewl.smarthome.service.impl;

import com.aliyuncs.iot.model.v20170420.PubResponse;
import io.github.futurewl.smarthome.dataobject.Device;
import io.github.futurewl.smarthome.repository.DeviceRepository;
import io.github.futurewl.smarthome.service.DeviceService;
import io.github.futurewl.smarthome.utils.AliYunIOTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Device find(Integer deviceId) {
        return deviceRepository.findOne(deviceId);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device update(Device device) {
        Device tempDevice = deviceRepository.findByDeviceName(device.getDeviceName());
        if (tempDevice != null) {
            device.setId(tempDevice.getId());
        }
        return deviceRepository.save(device);
    }

    @Override
    public Device save(Device device) {
        Device tempDevice = deviceRepository.findByDeviceName(device.getDeviceName());
        if (tempDevice != null) {
            device.setId(tempDevice.getId());
        }
        return deviceRepository.save(device);
    }

    @Override
    public Device exist(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName);
    }

    @Override
    public String action(String deviceName, String option) {
        PubResponse request = AliYunIOTUtil.getPubResponse("Yy2T9M7mKHJ", option, "/Yy2T9M7mKHJ/" + deviceName + "/get", 1);
        return request.getSuccess() ? "success" : "error";
    }

}
