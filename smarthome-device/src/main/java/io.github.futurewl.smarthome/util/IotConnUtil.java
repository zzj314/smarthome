package io.github.futurewl.smarthome.util;

import io.github.futurewl.smarthome.demo.iothub.ALiyunIotX509TrustManager;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/1/23
 * Time: 下午2:46
 * ==========================
 */
public class IotConnUtil {

    /**
     * 连接 MQTT
     *
     * @param url          连接地址
     * @param clientId     客户端ID
     * @param mqttUsername mqtt 用户名
     * @param mqttPassword mqtt 密码
     * @param deviceName   设备名称
     */
    public static void connection(String url, String clientId, String mqttUsername, String mqttPassword, String deviceName, String productKey) throws Exception {

        String subTopic = "/" + productKey + "/" + deviceName + "/get";
        String pubTopic = "/" + productKey + "/" + deviceName + "/update";
        String reqTopic = "/sys/" + productKey + "/" + deviceName + "/rrpc/request/+";

        MqttClient mqttClient = new MqttClient(url, clientId, new MemoryPersistence());
        LogUtil.print(clientId + "进行连接，目的地:" + url);
        MqttConnectOptions mqttConnectOptions = getMqttConnectOptions(mqttUsername, mqttPassword, createSSLSocket());
        mqttClient.connect(mqttConnectOptions);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                LogUtil.print("连接失败，原因:" + cause);
                cause.printStackTrace();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                LogUtil.print("接收消息，来自Topic [" + topic + "],内容是：[" + new String(message.getPayload(), "UTF-8") + "], ");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                LogUtil.print("消息发送成功！" + ((token == null || token.getResponse() == null) ? "null" : token.getResponse().getKey()));
            }
        });
        LogUtil.print("连接成功");

        // 发布
        mqttClient.publish(pubTopic, new MqttMessage("{\"concentration\":\"100\",\"option\":\"0\"}".getBytes("utf-8")));
        mqttClient.publish(pubTopic, new MqttMessage("{\"concentration\":\"100\",\"option\":\"0\"}".getBytes("utf-8")));

        // 订阅
        mqttClient.subscribe(subTopic, (topic, message) -> {
            LogUtil.print("收到消息：" + message + ":topic=" + topic);
        });

        // 订阅 rrpc
        mqttClient.subscribe(reqTopic, ((topic, message) -> {


            String payload = new String(message.getPayload(), "utf-8");
            String resultMessage = "关灯".equals(payload) ? "我关灯了" : "我开灯了";


            LogUtil.print("收到请求：" + message + ",topic=" + topic);
            String messageId = topic.substring(topic.lastIndexOf('/') + 1);
            String respTopic = "/sys/" + productKey + "/" + deviceName + "/rrpc/response/" + messageId;
            MqttMessage mqttMessage = new MqttMessage(resultMessage.getBytes());
            mqttMessage.setQos(0);
            new ThreadPoolExecutor(
                    2,
                    4,
                    600,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            ).submit(() -> {
                try {
                    mqttClient.publish(respTopic, mqttMessage);
                    LogUtil.print("回复响应成功,topic=" + respTopic);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            });
        }));
    }

    /**
     * 创建 ConnectOptions
     *
     * @param mqttUsername  用户名
     * @param mqttPassword  密码
     * @param socketFactory 安全连接
     * @return 连接配置
     */
    public static MqttConnectOptions getMqttConnectOptions(String mqttUsername, String mqttPassword, SSLSocketFactory socketFactory) {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
//        connectOptions.setSocketFactory(socketFactory);
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setCleanSession(false);
        connectOptions.setUserName(mqttUsername);
        connectOptions.setPassword(mqttPassword.toCharArray());
        connectOptions.setKeepAliveInterval(65);
        return connectOptions;
    }

    /**
     * 创建 SSL Socket 隧道
     *
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory createSSLSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLSV1.2");
        context.init(null, new TrustManager[]{new ALiyunIotX509TrustManager()}, null);
        return context.getSocketFactory();
    }

}
