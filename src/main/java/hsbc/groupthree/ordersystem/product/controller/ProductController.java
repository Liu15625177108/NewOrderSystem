package hsbc.groupthree.ordersystem.product.controller;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductRepository;
import hsbc.groupthree.ordersystem.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ProductController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.product.controller
 * @Date 2018/8/16 23:18
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    ProductRepository productRepository;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @Description: get products and sort by product's type
     * @Author: @Evan
     * @CreateDate: 2018/8/5 22:14
     * @UpdateDate: 2018/8/5 22:14
     * @Version: 1.0
     */

    @GetMapping("/productIndex")
    public ModelAndView productIndex() {
        ModelAndView mv = new ModelAndView("displayProduct.html");
        return mv;
    }

    @RequestMapping(value = "/user/get/products", method = RequestMethod.POST)
    public Page<ProductInfo> getProduct(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNum, @RequestParam(name = "dataCount", defaultValue = "2") int dataCount, @RequestParam(name = "productType", defaultValue = "短期型") String productType) {
        Sort sort = Sort.by("productType");
        Page<ProductInfo> products = productService.getProductListByPage(pageNum,productType,dataCount, sort);

        return products;
    }

    @PostMapping("/findProduct/byProductCodeOrProductName")
    public List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName) {
        List<ProductInfo> productList = productService.findByProductCodeOrProductName(productCode,productName);
        return productList;
    }
}
