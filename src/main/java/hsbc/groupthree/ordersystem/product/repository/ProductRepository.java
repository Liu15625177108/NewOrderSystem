package hsbc.groupthree.ordersystem.product.repository;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ProductRepository
 * @Author:Evan
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.repository
 * @Date 2018/8/16 23:21
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {
<<<<<<< HEAD

    List<ProductInfo> findByStatus(int i);

    default Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable) {
        return null;
    }

    /**
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

=======
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a
    /**
     * @Author Chen
     * @Description //TODO get ProductInfo by ProductId
     * @Date 14:20 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo findByProductId(String productId);
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
