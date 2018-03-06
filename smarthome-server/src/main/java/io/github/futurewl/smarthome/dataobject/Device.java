package io.github.futurewl.smarthome.dataobject;

import lombok.Data;

import javax.persistence.*;

/**
 * 设备实体
 *
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午5:48
 * ==========================
 */
@Data
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String deviceName;
    private String onlineStatus;
    private String switchStatus;
    private String poisonValue;
    private String lightValue;
}
