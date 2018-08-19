/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: UserServiceImpl
 * Author:   ca
 * Date:     2018/8/17 12:08
 * Description: the impl of userservice
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/17 12:08     1.0              the impl of userservice
 */
package hsbc.groupthree.ordersystem.user.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chen
 * @description〈the impl of userservice〉
 * @create 2018/8/17
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO to validate money
     * @Date 11:27 2018/8/15
     * @Param [userInfo, productInfo]
     **/
    @Override
    public boolean toValidateMoney(UserInfo userInfo, ProductInfo productInfo) {
        if (userInfo.getBalance() >  productInfo.getProductPrice()) {
            return true;
        }
        return false;
    }

    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO To validate paypassword
     * @Date 5:55 2018/8/3
     * @Param [UserInfo userInfo,String payPassword]
     **/
    @Override
    public boolean toValidatePayPassword(UserInfo userInfo, String payPassword) {

        if (payPassword != null || !payPassword.equals(" ")) {
            if (payPassword.equals(userInfo.getPayPassword())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @return UserInfo
     * @Author Chen
     * @Description //TODO to get userinfo by userid
     * @Date 12:38 2018/8/9
     * @Param [userId]
     **/
    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        UserInfo userInfo = userRepository.getOne(userId);
        return userInfo;
    }

    /**
     * @return
     * @Author Chen
     * @Description //TODO test
     * @Date 14:36 2018/8/13
     * @Param
     **/
    @Override
    public void addTest(UserInfo userInfo) {
//        userRepository.save(userInfo);
    }
}