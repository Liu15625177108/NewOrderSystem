package hsbc.groupthree.ordersystem.product.repository;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ProductRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.repository
 * @Date 2018/8/16 23:21
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {
    /**
     * @Author Chen
     * @Description //TODO get ProductInfo by ProductId
     * @Date 14:20 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo findByProductId(String productId);
}
