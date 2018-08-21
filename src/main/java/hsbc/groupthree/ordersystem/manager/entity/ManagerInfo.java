package hsbc.groupthree.ordersystem.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName ManagerInfo
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.entity
 * @Date 2018/8/16 23:15
 */
@Entity
public class ManagerInfo {
    @Id
    private String  workerNum;                              //bank manager's work number
    private String  workerName;                             //bacnk manager's name;
    private String  workerDepartment;                       // the department of bank manager
    private String  loginPassword;                          // the password for login.

    public  ManagerInfo(){}
    public ManagerInfo(String workerNum, String workerName, String workerDepartment, String loginPassword) {
        this.workerNum = workerNum;
        this.workerName = workerName;
        this.workerDepartment = workerDepartment;
        this.loginPassword = loginPassword;
    }

    public String getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(String workerNum) {
        this.workerNum = workerNum;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerDepartment() {
        return workerDepartment;
    }

    public void setWorkerDepartment(String workerDepartment) {
        this.workerDepartment = workerDepartment;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
