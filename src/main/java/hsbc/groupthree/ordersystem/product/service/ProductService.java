package hsbc.groupthree.ordersystem.product.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Author:Evan
 * @Date:2018/8/2 14:47
 * @Describe：
 * @Return:
 * @Param:
 */
public interface ProductService {
    /**
     * @return :list
     * @Description: get the all products
     * @Author: @Evan
     * @CreateDate: 2018/8/5 22:16
     * @UpdateDate: 2018/8/5 22:16
     * @Version: 1.0
     */

    Page<ProductInfo> getProductListByPage(int page, String productType, int count, Sort sort);

    /**
     * @Author Chen
     * @Description //TODO
     * @Date 14:18 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo getProductInfoByProductId(String productId);
    /**
     *
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName);


}
