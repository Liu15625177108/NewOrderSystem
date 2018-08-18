<<<<<<< HEAD
package hsbc.groupthree.ordersystem.product.service;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Page<ProductInfo> getProductListByPage(int page, String productType, int count, Sort sort) {
        Specification<ProductInfo> specification = new Specification<ProductInfo>() {
            @Override
            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.in(root.get("status")).value(1);

                List<Predicate> predicates = new ArrayList<Predicate>();
                Path<Long> status = root.get("status");
                Predicate predicate = criteriaBuilder.equal(status, 1);
                predicates.add(predicate);
                Path<Long> path = root.get("productType");
//               String productType= String
                Predicate predicate1 = criteriaBuilder.equal(path, productType);
                predicates.add(predicate1);
                return criteriaBuilder.and(predicates
                        .toArray(new Predicate[] {}));
            }
        };

        Pageable pageable = PageRequest.of(page, count, sort);
        return productRepository.findAll(specification, pageable);

//        Page<Product> products= productRepository.findAll();

    }

=======
/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: ProductServiceImpl
 * Author:   ca
 * Date:     2018/8/17 14:15
 * Description: the impl of product
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/17 14:15     1.0              the impl of product
 */
package hsbc.groupthree.ordersystem.product.service;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description〈the impl of product〉
 * @author Chen
 * @create 2018/8/17
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a
    @Override
    public ProductInfo getProductInfoByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }
<<<<<<< HEAD

    @Override
    public List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName) {
        return productRepository.findByProductCodeOrProductName(productCode,productName);
    }
}
=======
}
>>>>>>> dc75e86d3178cb9b8c79c698dfa81f194bcfd35a
