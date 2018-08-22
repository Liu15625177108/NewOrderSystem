package hsbc.groupthree.ordersystem.user.repository;



import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface UserRepository extends JpaRepository<UserInfo, String> {
    
    List<UserInfo> findByUsername(String username);

    /**
     *  to get a user info by username
     * @param : [username]
     * @return hsbc.groupthree.ordersystem.user.entity.UserInfo
     * @author :Jeff.Li
    */
    UserInfo findOneByUsername(String username);



    /**
     * @Author Chen
     * @Description //TODO
     * @Date 9:12 2018/8/15
     * @Param [userId, userMoney]
     * @return void
     **/
    @Modifying
    @Query("update UserInfo u set u.balance = ?2 where u.userId=?1")
    void updateUserInfoByUserId( String userId,double balance);

}
