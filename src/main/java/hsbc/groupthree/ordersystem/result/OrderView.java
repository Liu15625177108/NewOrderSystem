/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: OrderView
 * Author:   ca
 * Date:     2018/8/13 10:36
 * Description: the view
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/13 10:36     1.0              the view
 */
package hsbc.groupthree.ordersystem.result;

import hsbc.groupthree.ordersystem.commons.utils.CommonsUtils;
import hsbc.groupthree.ordersystem.commons.utils.DataUtils;
import hsbc.groupthree.ordersystem.order.entity.OrderInfo;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description〈the view〉
 * @author Chen
 * @create 2018/8/13
 * @since 1.0.0
 */
@Component
public class OrderView {
    @Autowired
    private  CommonsUtils commonsUtils;
    @Autowired
    private  DataUtils dataUtils;
    /**
     * @Author Chen
     * @Description //TODO  get orderInfo
     * @Date 11:28 2018/8/15
     * @Param [productInfo, userInfo]
     * @return hsbc.team03.ordersystem.toorder.product.OrdersInfo
     **/
    public OrderInfo getOrderInfo(ProductInfo productInfo, UserInfo userInfo){
        OrderInfo orderInfo=new OrderInfo(commonsUtils.getUUID(),productInfo.getProductName(),
                userInfo.getUsername(),userInfo.getPhone(), userInfo.getAddress(),
                1,dataUtils.getCurrentTime(),productInfo.getProductDuedate(),
                productInfo.getProductPrice(),productInfo.getProductCode());
        return  orderInfo;
    }
}