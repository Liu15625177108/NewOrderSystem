package hsbc.groupthree.ordersystem.user.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: info of users' login
 * @Author: Jeff.Li
 * @Created: 2018年08月03日 10:23:24
 **/
@Entity
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
    private String Email;

    private String phone;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date lastmodifiedTime;

    public UserInfo(String userId, @NotNull @Size(min = 6, max = 20) String username, @NotNull @Size(min = 6) String password, @NotNull String realName, int gender, @DecimalMax("99") int age, String position, String income, double balance, @javax.validation.constraints.Email String email, String phone, Date createTime, Date lastmodifiedTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.gender = gender;
        this.age = age;
        this.position = position;
        this.income = income;
        this.balance = balance;
        Email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.lastmodifiedTime = lastmodifiedTime;
    }

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastmodifiedTime() {
        return lastmodifiedTime;
    }

    public void setLastmodifiedTime(Date lastmodifiedTime) {
        this.lastmodifiedTime = lastmodifiedTime;
    }
}
