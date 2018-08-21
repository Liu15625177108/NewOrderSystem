
package hsbc.groupthree.ordersystem.product.service;
import hsbc.groupthree.ordersystem.commons.utils.PageableTool;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Evan
 * @Date:2018/8/2 14:49
 * @Describe：
 * @Return:
 * @Param:
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final PageableTool pageableTool;
    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(PageableTool pageableTool, ProductRepository productRepository) {
        this.pageableTool = pageableTool;
        this.productRepository = productRepository;
    }


    @Override
    public Page<ProductInfo> getProductListByPage(int page, String productType, int count, Sort sort) {

        Specification<ProductInfo> specification=pageableTool.specifucation(productType);

        Pageable pageable = PageRequest.of(page, count, sort);
        return productRepository.findAll(specification, pageable);
    }
    
    /**
     * @Author Chen
     * @Description //TODO getProductInfo by productId
     * @Date 15:13 2018/8/18
     * @Param [productId]
     * @return hsbc.groupthree.ordersystem.product.entity.ProductInfo
     **/
    @Override
    public ProductInfo getProductInfoByProductId(String productCode) {
        return productRepository.findByProductCode(productCode);
    }


    @Override
    public List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName) {
        return productRepository.findByProductCodeOrProductName(productCode,productName);
    }
}
