package hsbc.groupthree.ordersystem.commons.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.commons.security
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月21日 16:13:55
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserInfoServicesImpl jwtUserInfoService;

    @Autowired
    public UserSecurityConfig(JwtUserInfoServicesImpl jwtUserInfoService) {
        this.jwtUserInfoService = jwtUserInfoService;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserInfoService).passwordEncoder(new BCryptPasswordEncoder());
    }



    @Bean
    public JwtAthenticationFilter authenticationFilter() throws Exception {
        return new JwtAthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/user/register/**").permitAll()
                .antMatchers("/user/login/**").permitAll()
                .antMatchers("/order/**").hasRole("USER")
                .antMatchers("/manager/login").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .anyRequest().authenticated();
//                .anyRequest().hasRole("USER");
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
