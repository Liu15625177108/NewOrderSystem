package hsbc.groupthree.ordersystem.user.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: info of users' login
 * @Author: Jeff.Li
 * @Created: 2018年08月03日 10:23:24
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserInfo implements Serializable {
    @Id
    private String userId;

    @Column(unique = true)
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    private String realName;

    private int gender;

    @DecimalMax("99")
    private int age;

    private String position;

    private String income;

    private double balance;

    @Email
    private String email;

    private String phone;

    private String address;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<RoleInfo> roles = new HashSet<>();

    private String payPassword;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date lastmodifiedTime;

    public UserInfo() {
    }

    public UserInfo(String userId, @NotNull @Size(min = 6, max = 20) String username, @NotNull @Size(min = 6) String password, @NotNull String realName, int gender, @DecimalMax("99") int age, String position, String income, double balance, @Email String email, String phone, String address, String payPassword, Date createTime, Date lastmodifiedTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.gender = gender;
        this.age = age;
        this.position = position;
        this.income = income;
        this.balance = balance;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.payPassword = payPassword;
        this.createTime = createTime;
        this.lastmodifiedTime = lastmodifiedTime;
    }

    public UserInfo(String userId, String username,
                    double balance, String payPassword,
                    String phone, String address) {
        this.userId = userId;
        this.username = username;
        this.balance = balance;
        this.payPassword = payPassword;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userId, userInfo.userId) &&
                Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(realName, userInfo.realName) ;

    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, realName);
    }
}
