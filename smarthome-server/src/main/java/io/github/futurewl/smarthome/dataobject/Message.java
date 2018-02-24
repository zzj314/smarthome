package io.github.futurewl.smarthome.dataobject;

import io.github.futurewl.smarthome.utils.Base64Util;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;


/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午5:48
 * ==========================
 */
@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String messageType;
    private String payload;
    private String timestamp;

    @Override
    public String toString() {
        try {
            payload = Base64Util.decode(payload, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "MessageInfo{" +
                "payload='" + payload + '\'' +
                ", messagetype='" + messageType + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
