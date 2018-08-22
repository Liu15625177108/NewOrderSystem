package hsbc.groupthree.ordersystem.user.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.user.service
 * @Program: NewOrderSystem
 * @Description: service of userinfo
 * @date : 2018年08月22日 15:09:06
 **/
@Service
public class UserInfoServicesImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserInfoServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean toValidateMoney(UserInfo userInfo, ProductInfo productInfo) {
        return false;
    }

    @Override
    public boolean toValidatePayPassword(UserInfo userInfo, String payPassword) {
        return false;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return userRepository.findOneByUsername(userId);
    }

    public boolean updateUserInfo(UserInfo userInfo){
        return userRepository.saveAndFlush(userInfo)!=null;
    }
}
