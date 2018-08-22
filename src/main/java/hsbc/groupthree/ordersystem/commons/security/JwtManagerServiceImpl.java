package hsbc.groupthree.ordersystem.commons.security;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.manager.repository.ManagerLoginRepository;
import hsbc.groupthree.ordersystem.user.entity.RoleInfo;
import hsbc.groupthree.ordersystem.commons.security.JwtManagerInfo;
import hsbc.groupthree.ordersystem.manager.repository.ManagerLoginRepository;
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
 * @Package: hsbc.groupthree.ordersystem.commons.security
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月22日 15:54:22
 **/
@Service
public class JwtManagerServiceImpl implements UserDetailsService {
    private final ManagerLoginRepository managerRepository;

    @Autowired
    public JwtManagerServiceImpl(ManagerLoginRepository managerRepository) {
        this.managerRepository = managerRepository;
    }


    /**
     * to load user by username
     *
     * @param : [s]
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ManagerInfo managerInfo=managerRepository.findOneByWorkerNum(username);
        JwtManagerInfo jwtManagerInfo = new JwtManagerInfo(managerInfo.getWorkerNum(),
                username,
                managerInfo.getLoginPassword());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        jwtManagerInfo.setAuthorities(authorities);
        return jwtManagerInfo;
    }
}
