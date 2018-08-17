package hsbc.groupthree.ordersystem.product.repository;

import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:Evan
 * @Date:2018/8/12 16:56
 * @Describe：
 * @Return:
 * @Param:
 */
public interface ProductTypeRepository extends JpaRepository<ProductTypeInfo, Integer> {
}
