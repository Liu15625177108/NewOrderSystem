package hsbc.groupthree.ordersystem.product.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
=======
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a

/**
 * @Author:Evan
 * @Date:2018/8/2 14:47
 * @Describe：
 * @Return:
 * @Param:
 */
public interface ProductService {
<<<<<<< HEAD
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
=======
    
    /**
     * @Author Chen
     * @Description //TODO 
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a
     * @Date 14:18 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo getProductInfoByProductId(String productId);
<<<<<<< HEAD
    /**
     *
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName);


=======
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a
}
