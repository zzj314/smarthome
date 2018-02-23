package io.github.futurewl.smarthome.repository;

import io.github.futurewl.smarthome.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weilai <br/>
 * ==========================
 * Created with IDEA
 * Date: 2018/2/23
 * Time: 下午9:09
 * ==========================
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
