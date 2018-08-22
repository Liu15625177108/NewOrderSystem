package hsbc.groupthree.ordersystem.user.service;


import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: login service implement
 * @Author: Jeff.Li
 * @Created: 2018年08月03日 10:32:43
 **/
@Service
public class LoginServicesImpl implements LoginServices{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginServicesImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @Description to get a user by username
     * @param : [username]
     * @return hsbc.team03.ordersystem.loginregister.pojo.UserInfo
     *
     */
    @Override
    public UserInfo findUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    /**
     *  to get user id by using username
     * @param : [username]
     * @return java.lang.String
     *
    */
    public String getUserIdByUsername(String username){
        UserInfo userInfo = userRepository.findOneByUsername(username);
        if(userInfo == null){
            return null;
        }
        return userInfo.getUserId();
    }

    /**
     *  judge if login successful
     * @param : [username, password]
     * @return java.lang.String
     *
    */
    public String loginJudge(String username,String password){
        String result="";
        UserInfo userInfo = findUserByUsername(username);
        if(userInfo == null){
            result = "user not exist";
        } else if(passwordEncoder.matches(password,userInfo.getPassword())){
            result = "OK";
        } else {
            result = "wrong password";
        }
        return result;
    }
}
