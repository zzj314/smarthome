package io.github.futurewl.smarthome.service;

import io.github.futurewl.smarthome.dataobject.Device;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午8:54
 * ==========================
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void save() {
        Device device = new Device();
        Device device1 = deviceService.save(device);
        Assert.assertNotNull(device1);
        log.debug(device1.toString());
    }

    @Test
    public void findAll(){
       Assert.assertNotNull(deviceService.findAll());
    }
}