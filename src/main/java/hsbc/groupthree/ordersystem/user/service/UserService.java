package hsbc.groupthree.ordersystem.user.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;

/**
 * @ClassName UserService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.user.service
 * @Date 2018/8/16 23:22
 */
public interface UserService {

    /**
     * @Author Chen
     * @Description //TODO 
     * @Date 13:45 2018/8/2
     * @Param [userInfo, productInfo]
     * @return boolean
     **/
    boolean toValidateMoney(UserInfo userInfo,ProductInfo productInfo);
    /**
     * @Author Chen
     * @Description //TODO To validate pay password
     * @Date 5:56 2018/8/3
     * @Param [UserInfo userInfo,String payPassword]
     * @return boolean
     **/
    boolean toValidatePayPassword(UserInfo userInfo,String payPassword);

    /**
     * @Author Chen
     * @Description //TODO to get userinfo by userid
     * @Date 12:36 2018/8/9
     * @Param [userId]
     * @return UserInfo
     **/
    UserInfo getUserInfoByUserId(String userId);


}
