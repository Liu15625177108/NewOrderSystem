package hsbc.groupthree.ordersystem.commons.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.commons.security
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月21日 16:17:22
 **/
@Data
public class JwtUserInfo implements UserDetails {

    private final String userId;

    private final String username;

    private final String password;

    private List<? extends GrantedAuthority> authorities;

    private Date lastPasswordResetDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
