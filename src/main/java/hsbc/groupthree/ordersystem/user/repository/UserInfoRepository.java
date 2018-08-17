package hsbc.groupthree.ordersystem.user.repository;



import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Jeff.Li
 * @Package: hsbc.team03.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: user info dao
 * @date : 2018年08月10日 09:40:01
 **/
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    List<UserInfo> findByUsername(String username);

    UserInfo findOneByUsername(String username);
}
