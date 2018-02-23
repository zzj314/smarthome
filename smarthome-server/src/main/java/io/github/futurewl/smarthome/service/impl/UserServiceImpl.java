package io.github.futurewl.smarthome.service.impl;

import io.github.futurewl.smarthome.dataobject.User;
import io.github.futurewl.smarthome.repository.UserRepository;
import io.github.futurewl.smarthome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:12
 * ==========================
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
