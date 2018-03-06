package io.github.futurewl.smarthome.controller;

import io.github.futurewl.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:14
 * ==========================
 */
@Controller
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/deviceList")
    public String deviceList(Model model) {
        model.addAttribute("devices", deviceService.findAll());
        return "device_list";
    }

    @GetMapping("/deviceInfo")
    public String device(Integer deviceId, Model model) {
        model.addAttribute("device", deviceService.find(deviceId));
        return "device_info";
    }

}
