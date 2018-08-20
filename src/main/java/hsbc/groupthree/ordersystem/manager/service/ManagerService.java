package hsbc.groupthree.ordersystem.manager.service;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.result.ResultInfo;

/**
 * @ClassName ManagerService
 * @Author:Jerry.Liu;
 * @Description://the interface of mananger's level,include  login,modify the product,check the order.
 * @Package hsbc.groupthree.ordersystem.manager.service
 * @Date 2018/8/16 23:15
 */
public interface ManagerService {
    public boolean login(String workerNum,String password);

    public ResultInfo findByworkerNum(String workerNum);

    public ResultInfo findAllOrder();

    public ResultInfo findOrderByUsername();

    public ResultInfo findOrderByDate();



}
