package hsbc.groupthree.ordersystem.product.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;

/**
 * @ClassName ProductService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.service
 * @Date 2018/8/16 23:19
 */
public interface ProductService {
    
    /**
     * @Author Chen
     * @Description //TODO 
     * @Date 14:18 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo getProductInfoByProductId(String productId);
}
