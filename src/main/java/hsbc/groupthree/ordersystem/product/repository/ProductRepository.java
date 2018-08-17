package hsbc.groupthree.ordersystem.product.repository;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.repository
 * @Date 2018/8/16 23:21
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByStatus(int i);

    Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable);

    /**
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);
}
