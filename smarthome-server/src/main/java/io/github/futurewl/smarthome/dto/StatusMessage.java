package io.github.futurewl.smarthome.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StatusMessage {
    private String clientIp;
    private String deviceName;
    private String productKey;
    private String status;
    private Date lastTime;
    private Date time;
}
