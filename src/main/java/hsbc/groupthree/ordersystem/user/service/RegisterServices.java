package hsbc.groupthree.ordersystem.user.service;

import hsbc.groupthree.ordersystem.user.entity.UserInfo;

import java.util.List;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.user.service
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月22日 09:37:00
 **/
public interface RegisterServices {
    /**
     * @param : []
     * @return java.util.List<hsbc.team03.ordersystem.loginregister.pojo.UserInfo>
     * @Description to get all the user
     */
    List<UserInfo> findAllUser();

    /**
     * @param : [username]
     * @return hsbc.team03.ordersystem.loginregister.pojo.UserInfo
     * @Description to get user by username
     */
    UserInfo findUserByUsername(String username);

    /**
     * @param : [userInfo]
     * @return boolean
     * @Description to add a user
     */
    boolean addUser(UserInfo userInfo);
}
