package hsbc.groupthree.ordersystem.order.repository;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName OrderRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.repository
 * @Date 2018/8/16 23:20
 */
public interface OrderRepository extends JpaRepository<OrderInfo,String> {
    OrderInfo findByorderId(String orderId);
}
