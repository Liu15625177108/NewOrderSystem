/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: OrderServiceImpl
 * Author:   ca
 * Date:     2018/8/17 11:35
 * Description: the impl of ordersevice
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/17 11:35     1.0              the impl of ordersevice
 */
package hsbc.groupthree.ordersystem.order.service;

import hsbc.groupthree.ordersystem.commons.utils.CommonsUtils;
import hsbc.groupthree.ordersystem.commons.utils.DataUtils;
import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.order.repository.OrderRepository;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductRepository;
import hsbc.groupthree.ordersystem.product.repository.ProductTypeRepository;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description〈the impl of ordersevice〉
 * @author Chen
 * @create 2018/8/17
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private CommonsUtils commonsUtils;
    
    @Autowired
    private DataUtils dataUtils;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    /**
     * @return java.lang.Object
     * @Author Chen
     * @Description //TODO TO place the order
     * @Date 5:53 2018/8/3
     * @Param [ProductInfo productInfo,UserInfo userInfo]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized  boolean insertOrder(ProductInfo productInfo, UserInfo userInfo) {
        OrderInfo orderInfo = new OrderInfo(commonsUtils.getUUID(), productInfo.getProductName(),
                 userInfo.getUsername(), userInfo.getPhone(), userInfo.getAddress(), 
                1, dataUtils.getCurrentTime(),productInfo.getProductDuedate(),
                getOrderPrice(productInfo),productInfo.getProductCode());
        orderRepository.save(orderInfo);
        double userMoney = userInfo.getBalance() - getOrderPrice(productInfo);
//        int i=1/0;
        //to reduce money
        userRepository.updateUserInfoByUserId(userInfo.getUserId(), userMoney);
        return true;
    }

    /**
     * @return double
     * @Author Chen
     * @Description //TODO To get orderprice
     * @Date 5:54 2018/8/3
     * @Param [int productNumber,double productPrice]
     **/
    @Override
    public double getOrderPrice(ProductInfo productInfo) {
        double orderPice =  productInfo.getProductPrice();
        return orderPice;
    }

    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO
     * @Date 10:21 2018/8/10
     * @Param [orderId]
     **/
    @Override
    public boolean determineTime(String orderId) {
        OrderInfo orderInfo = orderRepository.findByorderId(orderId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        try {
            //get order time
            Date date = simpleDateFormat.parse(orderInfo.getStartTime());
            //get current time to reduce 7 days
            Date nowDate = new Date();
            Date beforeDate = new Date(nowDate.getTime() - (long)7 * 24 * 60 * 60 * 1000);
            if (beforeDate.before(date)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return hsbc.team03.ordersystem.toorder.product.OrdersInfo
     * @Author Chen
     * @Description //TODO get orderInfo by orderid
     * @Date 11:38 2018/8/10
     * @Param [orderId]
     **/
    @Override
    public OrderInfo getOrderInfoByOrderId(String orderId) {
        return orderRepository.findByorderId(orderId);
    }

    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO update OrderStatus to 2 and rollback money when cancel order
     * @Date 11:39 2018/8/10
     * @Param [OrderId]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderStatus(String orderId) {
        OrderInfo orderInfo=orderRepository.findByorderId(orderId);
        orderInfo.setOrderStatus(2);
        orderRepository.save(orderInfo);
        //get userinfo by username in order to rollback money
        UserInfo userInfo=userRepository.findOneByUsername(orderInfo.getUserName());
        double nowMoney=userInfo.getBalance();
        userInfo.setBalance(nowMoney+orderInfo.getTotalMoney());
        userRepository.save(userInfo);
        return false;
    }
    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO get all orderinfo
     * @Date 11:39 2018/8/10
     * @Param [OrderId]
     **/
    @Override
    public List<OrderInfo> findAll() {
        return orderRepository.findAll();
    }

    /**
     * @return void
     * @Author Chen
     * @Description //TODO to account by time
     * @Date 11:39 2018/8/10
     * @Param [OrderId]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void timeAccount(String orderId) {
        OrderInfo orderInfo=orderRepository.findByorderId(orderId);
        orderInfo.setOrderStatus(3);
        orderRepository.save(orderInfo);
        //get productinfo by productcode
        ProductInfo productInfo=productRepository.findByProductCode(orderInfo.getProductCode());
        ProductTypeInfo productTypeInfo=productTypeRepository.findByProductType(productInfo.getProductType());
        //get userinfo by username in order to rollback money
        UserInfo userInfo=userRepository.findOneByUsername(orderInfo.getUserName());
        double nowMoney=userInfo.getBalance();
        String rate=productTypeInfo.getRate();

            rate=rate.replace("%","");
            Double r=Double.valueOf(rate)/100;
            System.out.print(r);

        double accountMoney=r*orderInfo.getTotalMoney()+orderInfo.getTotalMoney();
        userInfo.setBalance(nowMoney+orderInfo.getTotalMoney());
        userRepository.save(userInfo);
    }

    /**
     * @return void
     * @Author Chen
     * @Description //TODO test
     * @Date 11:39 2018/8/10
     * @Param
     **/
    @Test
    public void test1(){
    }
}