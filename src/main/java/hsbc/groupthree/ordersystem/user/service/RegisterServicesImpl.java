package hsbc.groupthree.ordersystem.user.service;


import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: register service implement
 * @Author: Jeff.Li
 * @Created: 2018年08月03日 10:36:04
 **/
@Service
public class RegisterServicesImpl implements RegisterServices {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public RegisterServicesImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * @param : []
     * @return java.util.List<hsbc.team03.ordersystem.loginregister.pojo.UserInfo>
     * @Description to get all user implement
     */
    @Override
    public List<UserInfo> findAllUser() {
        return userInfoRepository.findAll();
    }

    /**
     * @Description to get user by username
     * @param : [username]
     * @return java.util.List<hsbc.team03.ordersystem.loginregister.pojo.UserInfo>
     *
     */
    @Override
    public UserInfo findUserByUsername(String username) {
        return userInfoRepository.findOneByUsername(username);

    }

    /**
     *  to add a user
     * @param : [userInfo]
     * @return boolean
     *
     */
    @Override
    public boolean addUser(UserInfo userInfo) {
        try {
            userInfo.setUserId(GenerateId.getUUID());
            userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
            Date date = new Date();
            userInfo.setCreateTime(date);
            userInfo.setLastmodifiedTime(date);
            userInfoRepository.save(userInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
