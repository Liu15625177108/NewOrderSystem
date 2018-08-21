package hsbc.groupthree.ordersystem.product.repository;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ProductRepository
 * @Author:Evan
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.repository
 * @Date 2018/8/16 23:21
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {


    /**
     * @descript: find product of which its status 1 or 2
     * @Method :findAllByStatus
     * @return
     */
    @Query(value = "SELECT * FROM product where status=?1 or status=?2",nativeQuery = true)
    List<ProductInfo> getProduct(int n,int m);


    List<ProductInfo> findByStatus(int i);

  Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable);

    /**
     * @param pageable
     * @return
     */
    @Override
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * @Author Chen
     * @Description //TODO get ProductInfo by ProductId
     * @Date 14:20 2018/8/17
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    ProductInfo findByProductCode(String productCode);

    /**
     *
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName);

}
