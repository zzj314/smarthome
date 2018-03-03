package io.github.futurewl.smarthome.controller;

import io.github.futurewl.smarthome.dataobject.Device;
import io.github.futurewl.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/device")
    public String device(Integer deviceId, Model model) {
//        Device device = deviceService.find(deviceId);
//        Device device = deviceService.find(deviceId);
        model.addAttribute("device", deviceId);
        return "device";
    }


    @GetMapping("/deviceList")
    public String deviceList(@RequestParam(value = "userId", required = true) String userId, Model model) {
//        List<Device> deviceList = deviceService;

        return "device-list";
    }
}
