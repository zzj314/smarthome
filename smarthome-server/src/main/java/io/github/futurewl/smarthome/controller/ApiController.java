package io.github.futurewl.smarthome.controller;

import io.github.futurewl.smarthome.dataobject.Device;
import io.github.futurewl.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/24
 * Time: 下午12:46
 * ==========================
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 对设备直接操作
     *
     * @param deviceName
     * @param option
     * @return
     */
    @PostMapping("/action")
    public String action(String deviceName, String option) {
        String result;
        result = deviceService.action(deviceName, option);
        return result;
    }

    /**
     * 获取设备信息
     *
     * @param deviceId
     * @return
     */
    @GetMapping("/device")
    public Device device(Integer deviceId) {
        return deviceService.find(deviceId);
    }

}
