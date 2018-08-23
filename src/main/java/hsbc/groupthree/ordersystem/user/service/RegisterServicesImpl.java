package hsbc.groupthree.ordersystem.user.service;


import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: register service implement
 * @Author: Jeff.Li
 * @Created: 2018年08月03日 10:36:04
 **/
@Service
public class RegisterServicesImpl  {
//
//    private final UserRepository userRepository;
//
//    private final CommonsUtils commonsUtils;
//
//    @Autowired
//    public RegisterServicesImpl(UserRepository userRepository, CommonsUtils commonsUtils) {
//        this.userRepository = userRepository;
//        this.commonsUtils = commonsUtils;
//    }

    /**
     * @param : []
     * @return java.util.List<hsbc.team03.ordersystem.loginregister.pojo.UserInfo>
     * @Description to get all user implement
     */
//    @Override
//    public List<UserInfo> findAllUser() {
//        return userRepository.findAll();
//    }

    /**
     * @Description to get user by username
     * @param : [username]
     * @return java.util.List<hsbc.team03.ordersystem.loginregister.pojo.UserInfo>
     *
     */
//    @Override
//    public UserInfo findUserByUsername(String username) {
//        return userRepository.findOneByUsername(username);
//
//    }

    /**
     *  to add a user
     * @param : [userInfo]
     * @return boolean
     *
     */
//    @Override
//    public boolean addUser(UserInfo userInfo) {
//        try {
//            userInfo.setUserId(commonsUtils.getUUID());
//           // userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
//            Date date = new Date();
//            userInfo.setCreateTime(date);
//            userInfo.setLastmodifiedTime(date);
//            userRepository.save(userInfo);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

}
