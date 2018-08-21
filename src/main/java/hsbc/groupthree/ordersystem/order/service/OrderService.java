package hsbc.groupthree.ordersystem.order.service;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;

import java.util.List;

/**
 * @ClassName OrderService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.service
 * @Date 2018/8/16 23:17
 */
public interface OrderService {
    /**
     * @Author Chen
     * @Description //TODO To place an order
     * @Date 21:12 2018/8/2
     * @Param [ProductInfo productInfo,UserInfo userInfo]
     * @return Object
     **/
    boolean insertOrder(ProductInfo productInfo, UserInfo userInfo);

    /**
     * @Author Chen
     * @Description //TODO To get all order price
     * @Date 21:12 2018/8/2
     * @Param [int productNumber,double productPrice]
     * @return double
     **/
    double getOrderPrice(ProductInfo productInfo);

    /**
     * @Author Chen
     * @Description //TODO cancelorder verify time
     * @Date 10:20 2018/8/10
     * @Param [orderId]
     * @return boolean
     **/
    boolean determineTime(String orderId);

    /**
     * @Author Chen
     * @Description //TODO  get orderinfo by orderid
     * @Date 10:24 2018/8/10
     * @Param [orderId]
     * @return OrdersInfo
     **/
    OrderInfo getOrderInfoByOrderId(String orderId);

    /**
     * @Author Chen
     * @Description //TODO 
     * @Date 11:36 2018/8/10
     * @Param [orderId]
     * @return boolean
     **/
    boolean updateOrderStatus(String orderId);

    /**
     * @Author Chen
     * @Description //TODO
     * @Date 11:36 2018/8/10
     * @Param
     * @return  List<OrderInfo>
     **/
    List<OrderInfo> findAll();

    /**
     * @Author Chen
     * @Description //TODO time account
     * @Date 11:36 2018/8/10
     * @Param
     * @return  List<OrderInfo>
     **/
   void timeAccount(String orderId);

}
