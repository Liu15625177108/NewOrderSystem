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
    @Override
    public ProductInfo getProductInfoByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }
}