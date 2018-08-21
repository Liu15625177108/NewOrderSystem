/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: TimeAccount
 * Author:   ca
 * Date:     2018/8/17 11:35
 * Description: the impl of ordersevice
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/17 11:35     1.0              TimeAccount
 */
package hsbc.groupthree.ordersystem.commons.utils;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.order.service.OrderService;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @description〈the impl of ordersevice〉
 * @author Chen
 * @create 2018/8/17
 * @since 1.0.0
 */
@Component
public class TimeAccount {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    /**
     * @return java.lang.Object
     * @Author Chen
     * @Description //TODO TO account money by scheduled
     * @Date 5:53 2018/8/3   /5 * * * * ?
     * @Param [ProductInfo productInfo,UserInfo userInfo]
     **/
    @Scheduled(cron = "0 0 12 * * ?")
    public void AccountMoney(){
        System.out.println("111");
        List<OrderInfo> orderInfoList=orderService.findAll();
        System.out.println(orderInfoList);
        Iterator i=orderInfoList.iterator();
        while (i.hasNext()){
            OrderInfo orderInfo= (OrderInfo) i.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date());
            System.out.println(time);
            System.out.println(orderInfo.getProductDuelate());
            if(orderInfo.getProductDuelate().equals(time)){
                if(orderInfo.getOrderStatus()==1){
                        orderService.timeAccount(orderInfo.getOrderId());
                }
            }
        }
    }
}
