package hsbc.groupthree.ordersystem.user.repository;

import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName UserRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.user.repository
 * @Date 2018/8/16 23:21
 */
public interface UserRepository extends JpaRepository<UserInfo,String> {

    /**
     * @Author Chen
     * @Description //TODO 
     * @Date 9:12 2018/8/15
     * @Param [userId, userMoney]
     * @return void
     **/
    @Modifying
    @Query("update UserInfo u set u.userMoney = ?2 where u.userId=?1")
    void updateUserInfoByUserId( String userId,double userMoney);
    UserInfo findByUserName (String userName);
}
