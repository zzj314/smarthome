package io.github.futurewl.smarthome.repository;

import io.github.futurewl.smarthome.dataobject.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午8:49
 * ==========================
 */
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
