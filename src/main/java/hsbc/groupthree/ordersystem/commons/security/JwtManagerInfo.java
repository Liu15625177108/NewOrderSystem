package hsbc.groupthree.ordersystem.commons.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.util.Collection;
import java.util.List;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.commons.security
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月22日 15:42:32
 **/
@Data
public class JwtManagerInfo implements UserDetails {


    private String  workerNum;
    private String  workerName;
    private String  workerDepartment;
    private String  loginPassword;
    private List<? extends GrantedAuthority> authorities;

    public JwtManagerInfo(String workerNum, String workerName, String loginPassword) {
        this.workerNum = workerNum;
        this.workerName = workerName;
        this.loginPassword = loginPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
