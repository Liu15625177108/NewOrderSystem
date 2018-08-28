package hsbc.groupthree.ordersystem.order.repository;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName OrderRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.repository
 * @Date 2018/8/16 23:20
 */
public interface OrderRepository extends JpaRepository<OrderInfo,String> {

    /**
     * @Method findByorderId
     * @Description //TODO  find order by orderId[it must be only one]
     * @Author Alan Ruan
     * @Date 2018/08/20 11:46:31
     * @Param [orderId]
     * @Return hsbc.groupthree.ordersystem.order.entity.OrderInfo
     */
    OrderInfo findByorderId(String orderId);


    /**
     * @Method showAllOrder
     * @Description //TODO find order by userName [it can be one or more]
     * @Author Alan Ruan
     * @Date 2018/08/20 14:21:46
     * @Param [userName]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @Modifying
    @Query("select od from OrderInfo od where od.userName=?1 and od.orderStatus=1")
    List<OrderInfo> findAllByUserName(String userName);


    /**
     * @Method findAllByStartTime
     * @Description //TODO  find order by time 订单下单时间 [it can be one or more]
     * @Author Alan Ruan
     * @Date 2018/08/20 17:20:58
     * @Param [startTime]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    List<OrderInfo> findAllByStartTime(String startTime);

    /**
     * @Method findAllByProductDuelate
     * @Description //TODO   find order by productDuelate 产品截止售卖时间 [it can be one or more]
     * @Author Alan Ruan
     * @Date 2018/08/22 14:59:34
     * @Param [productDuelate]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    List<OrderInfo> findAllByProductDuelate(String productDuelate);

    /**
     * @Method findAllByProductSelldate
     * @Description //TODO  find order by productSelldate 产品开始售卖时间 [it can be one or more]
     * @Author Alan Ruan
     * @Date 2018/08/22 15:03:10
     * @Param [productSelldate]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    List<OrderInfo> findAllByProductSelldate(String productSelldate);

}
