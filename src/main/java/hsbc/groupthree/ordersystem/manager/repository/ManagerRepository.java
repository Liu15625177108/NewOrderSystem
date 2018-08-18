package hsbc.groupthree.ordersystem.manager.repository;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ManagerRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.repository
 * @Date 2018/8/16 23:20
 */
@Repository
public interface ManagerRepository extends JpaRepository<ProductInfo, String> {
    /**
     * @param productCode
     * @return findProduct by productCode from database
     */
    ProductInfo findByProductCode(String productCode);

    Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable);



    /**
     *
     * @param id
     * @return
     */
    ProductInfo findByid(String id);
    /**
     * @param
     * @return list
     * @descripe:根据条件，获取产品的产品名和产品代码
     */
    List<ProductInfo> findByStatus(int n);

    /**
     *  manager find product by product's name or productCode
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode,String productName);


}