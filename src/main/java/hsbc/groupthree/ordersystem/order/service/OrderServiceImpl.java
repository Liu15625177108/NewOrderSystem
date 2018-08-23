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
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chen
 * @description〈the impl of ordersevice〉
 * @create 2018/8/17
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {

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
    public synchronized boolean insertOrder(ProductInfo productInfo, UserInfo userInfo) {
        OrderInfo orderInfo = new OrderInfo(commonsUtils.getUUID(), productInfo.getProductName(),
                userInfo.getUsername(), userInfo.getPhone(), userInfo.getAddress(),
                1, dataUtils.getCurrentTime(), productInfo.getProductDuedate(),
                productInfo.getProductSelldata(), getOrderPrice(productInfo), productInfo.getProductCode());
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
        double orderPice = productInfo.getProductPrice();
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
            Date beforeDate = new Date(nowDate.getTime() - (long) 7 * 24 * 60 * 60 * 1000);
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

        //return orderRepository.findByorderId(orderId);
        //因为远程库还没有数据，所以先造一些数据吧

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setOrderId("A11");
        orderInfo.setOrderStatus(1);
        orderInfo.setProductName("龙卡VIP");
        orderInfo.setProductNumber(2);
        orderInfo.setProductPrice(6.66);
        orderInfo.setUserName("小鑫");
        orderInfo.setUserAddress("华南师范大学西三425");
        orderInfo.setStartTime("2015年");
        orderInfo.setUserPhone("1234这是电话4321");
        orderInfo.setTotalMoney(13.32);
        orderInfo.setProductCode("22BB22");
        orderInfo.setProductSelldate("2018-08-21");
        orderInfo.setProductDuelate("2018-08-22");

        return orderInfo;
    }

    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO update OrderStatus to 2 and rollback money when cancel order
     * @Date 11:39 2018/8/10
     * @Param [OrderId]
     **/
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean updateOrderStatus(String orderId) {
//        OrderInfo orderInfo=orderRepository.findByorderId(orderId);
//        orderInfo.setOrderStatus(2);
//        orderRepository.save(orderInfo);
//        //get userinfo by username in order to rollback money
//        UserInfo userInfo=userRepository.findOneByUsername(orderInfo.getUserName());
//        double nowMoney=userInfo.getBalance();
//        userInfo.setBalance(nowMoney+orderInfo.getTotalMoney());
//        userRepository.save(userInfo);
//        return false;
//    }

    /**
     * @return boolean
     * @Author Chen
     * @Description //TODO update OrderStatus to 2 and rollback money when cancel order
     * @Date 11:39 2018/8/10
     * @Param [OrderId]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateOrderStatus(String orderId) throws ParseException {
        OrderInfo orderInfo = orderRepository.findByorderId(orderId);
        orderInfo.setOrderStatus(2);
        System.out.println(orderInfo.getProductSelldate()+"111");
        orderRepository.save(orderInfo);
        System.out.println(orderInfo.getProductSelldate()+"222");
        return dataUtils.reduceMoneyByTime(orderInfo);
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
        OrderInfo orderInfo = orderRepository.findByorderId(orderId);
        orderInfo.setOrderStatus(3);
        orderRepository.save(orderInfo);
        //get productinfo by productcode
        ProductInfo productInfo = productRepository.findByProductCode(orderInfo.getProductCode());
        ProductTypeInfo productTypeInfo = productTypeRepository.findByProductType(productInfo.getProductType());
        //get userinfo by username in order to rollback money
        UserInfo userInfo = userRepository.findOneByUsername(orderInfo.getUserName());
        double nowMoney = userInfo.getBalance();
        String rate = productTypeInfo.getRate();

        rate = rate.replace("%", "");
        Double r = Double.valueOf(rate) / 100;
        System.out.print(r);

        double accountMoney = r * orderInfo.getTotalMoney() + orderInfo.getTotalMoney();
        userInfo.setBalance(nowMoney + orderInfo.getTotalMoney());
        userRepository.save(userInfo);
    }

    /**
     * @Method findAllOrder
     * @Description //TODO list all order of this user(through userName)
     * @Author Alan Ruan
     * @Date 2018/08/20 11:49:18
     * @Param [userId]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @Override
    public List<OrderInfo> findAllOrder(String userName){

//        return orderRepository.findAllByUserName(userName);
        //因为远程库还没有数据，所以先造一些数据吧

        OrderInfo orderInfo = new OrderInfo();
        OrderInfo orderInfo1 = new OrderInfo();
        OrderInfo orderInfo2 = new OrderInfo();

        List<OrderInfo> orderInfoList = new ArrayList<>();

        orderInfo.setOrderId("A11");
        orderInfo.setOrderStatus(1);
        orderInfo.setProductName("龙卡VIP");
        orderInfo.setProductNumber(2);
        orderInfo.setProductPrice(6.66);
        orderInfo.setUserName("小鑫");
        orderInfo.setUserAddress("华南师范大学西三425");
        orderInfo.setStartTime("2015年");
        orderInfo.setUserPhone("1234这是电话4321");
        orderInfo.setTotalMoney(13.32);
        orderInfo.setProductCode("11AA11");
        orderInfo.setProductSelldate("2018-08-21");
        orderInfo.setProductDuelate("2018-08-22");

        orderInfo1.setOrderId("B11");
        orderInfo1.setOrderStatus(1);
        orderInfo1.setProductName("黑卡VIP");
        orderInfo1.setProductNumber(1);
        orderInfo1.setProductPrice(6.66);
        orderInfo1.setUserName("小鑫");
        orderInfo1.setUserAddress("华南师范大学西三425");
        orderInfo1.setStartTime("2015年");
        orderInfo1.setUserPhone("1234这是电话4321");
        orderInfo1.setTotalMoney(6.66);
        orderInfo1.setProductCode("22BB22");
        orderInfo1.setProductSelldate("2018-08-21");
        orderInfo1.setProductDuelate("2018-08-22");

        orderInfo2.setOrderId("C11");
        orderInfo2.setOrderStatus(1);
        orderInfo2.setProductName("Super VIP");
        orderInfo2.setProductNumber(2);
        orderInfo2.setProductPrice(6.66);
        orderInfo2.setUserName("小鑫");
        orderInfo2.setUserAddress("华南师范大学西三425");
        orderInfo2.setStartTime("2015年");
        orderInfo2.setUserPhone("1234这是电话4321");
        orderInfo2.setTotalMoney(13.32);
        orderInfo2.setProductCode("33BB33");
        orderInfo2.setProductSelldate("2018-08-21");
        orderInfo2.setProductDuelate("2018-08-22");

        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo1);
        orderInfoList.add(orderInfo2);

        return orderInfoList;

    }

    /**
     * @Method findOrderByDate
     * @Description //TODO  get orderinfo by date
     * @Author Alan Ruan
     * @Date 2018/08/21 16:49:53
     * @Param [date]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @Override
    public List<OrderInfo> findOrderByDate(String date) {
        return orderRepository.findAllByStartTime(date);
    }


    /**
     * @Method findOrderByProductSellDate
     * @Description //TODO
     * @Author Alan Ruan
     * @Date 2018/08/23 16:48:17
     * @Param [productSellDate]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @Override
    public List<OrderInfo> findOrderByProductSellDate(String productSellDate) {
        return orderRepository.findAllByProductSelldate(productSellDate);
    }


    /**
     * @Method findOrderByProductDuelate
     * @Description //TODO
     * @Author Alan Ruan
     * @Date 2018/08/23 16:48:22
     * @Param [productDuelate]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @Override
    public List<OrderInfo> findOrderByProductDuelate(String productDuelate) {
        return orderRepository.findAllByProductDuelate(productDuelate);
    }
}