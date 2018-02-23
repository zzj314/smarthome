package io.github.futurewl.smarthome.service;

import io.github.futurewl.smarthome.dataobject.Message;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:07
 * ==========================
 */
public interface MessageService {
    Message save(Message message);
}
