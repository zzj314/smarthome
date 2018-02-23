package io.github.futurewl.smarthome.service.impl;

import io.github.futurewl.smarthome.dataobject.Message;
import io.github.futurewl.smarthome.repository.MessageRepository;
import io.github.futurewl.smarthome.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:07
 * ==========================
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
