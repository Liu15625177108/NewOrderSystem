package hsbc.groupthree.ordersystem.commons.security;

import hsbc.groupthree.ordersystem.user.entity.RoleInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.RoleInfoRepository;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : Jeff.Li
 * @Package: hsbc.team03.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: userinfo in userDetail of spring security
 * @date : 2018年08月10日 11:08:59
 **/
@Service
public class JwtUserInfoServicesImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserInfoServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * to load user by username
     *
     * @param : [s]
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=userRepository.findOneByUsername(username);
        JwtUserInfo jwtUserInfo = new JwtUserInfo(userInfo.getUserId(),
                username,
                userInfo.getPassword());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(RoleInfo roleInfo : userInfo.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleInfo.getRoleName()));
        }
        jwtUserInfo.setAuthorities(authorities);
        return jwtUserInfo;
    }


}