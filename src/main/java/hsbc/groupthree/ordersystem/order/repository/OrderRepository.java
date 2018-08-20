package hsbc.groupthree.ordersystem.order.repository;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

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
     * @Description //TODO find order by userName[it can be one or more]
     * @Author Alan Ruan
     * @Date 2018/08/20 14:21:46
     * @Param [userName]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    List<OrderInfo> findAllByUserName(String userName);
}
