package io.github.futurewl.smarthome.config;

import com.alibaba.fastjson.JSON;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import io.github.futurewl.smarthome.dataobject.Device;
import io.github.futurewl.smarthome.dataobject.Message;
import io.github.futurewl.smarthome.dto.StatusMessage;
import io.github.futurewl.smarthome.service.DeviceService;
import io.github.futurewl.smarthome.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务获取消息队列数据
 *
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:19
 * ==========================
 */
@Slf4j
@Component
@EnableScheduling
public class SchedulingConfig {
    private String accessId = "LTAI9bFla9gmeY9G";
    private String accessKey = "eo5jtczEOEfPAh6rNDgkGoz3dPhqBn";
    private String accessEndpoint = "https://1057622711814778.mns.cn-shanghai.aliyuncs.com/";
    private String productKey = "Yy2T9M7mKHJ";

    @Autowired
    private MessageService messageService;
    @Autowired
    private DeviceService deviceService;

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduler() {
        CloudAccount account = new CloudAccount(accessId, accessKey, accessEndpoint);
        MNSClient client = account.getMNSClient();
        //参数请输入IoT自动创建的队列名称，例如上面截图中的aliyun-iot-3AbL0062osF
        CloudQueue queue = client.getQueueRef("aliyun-iot-" + productKey);
        // 获取消息
        //长轮询等待时间为10秒
        com.aliyun.mns.model.Message popMsg = queue.popMessage(10);

        if (popMsg != null) {
            String msg = popMsg.getMessageBodyAsString("UTF-8");
            try {
                new JsonParser().parse(msg);
            } catch (JsonParseException e) {
                log.error("错误的 JSON 串: " + msg);
                return;
            }
            Message message = JSON.parseObject(msg, Message.class);
//            System.out.println("PopMessage Body: " + messageInfo);
            log.info("PopMessage Body: " + message);//获取原始消息
            messageService.save(message);


            Device device = new Device();
            if (message.getMessageType().equals("status")) {
                StatusMessage statusMessage = JSON.parseObject(message.getPayload(), StatusMessage.class);
                device.setDeviceName(statusMessage.getDeviceName());
                device.setOnlineStatus(statusMessage.getStatus());
                deviceService.save(device);
            } else if (message.getMessageType().equals("upload")) {
                String payload = message.getPayload();
                String[] items = payload.split(",");
                device = deviceService.exist(items[0]);
                device.setPoisonValue(items[1]);
                device.setLightValue(items[2]);
                device.setSwitchStatus(items[3]);
                deviceService.update(device);
            }


            //从队列中删除消息
            queue.deleteMessage(popMsg.getReceiptHandle());
        } else {
//            System.out.println("Continuing");
            log.info("Continuing");
        }
    }
}
