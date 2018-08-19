package hsbc.groupthree.ordersystem.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import hsbc.groupthree.ordersystem.manager.service.ManagerService;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ManagerController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.controller
 * @Date 2018/8/16 23:16
 */
@RestController
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    /**
     * @Description:
     * @Author: @Evan
     * @CreateDate: 2018/8/5 21:56
     * @UpdateDate: 2018/8/5 21:56
     * @Version: 1.0
     */
    @RequestMapping(value = "/manager-index", method = RequestMethod.GET)
    public ModelAndView managerIndexPage() {
        ModelAndView modelAndView = new ModelAndView("managerProduct.html");
        return modelAndView;
    }


    /**
     * @Description: manager get all the production
     * @Author: @Evan
     * @CreateDate: 2018/8/5 21:56
     * @UpdateDate: 2018/8/5 21:56
     * @Version: 1.0
     */
    @RequestMapping(value = "/manager/productlist", method = RequestMethod.POST)
    public Page<ProductInfo> getAllProduct(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNum, @RequestParam(name = "dataCount", defaultValue = "2") int dataCount, @RequestParam(name = "productType", defaultValue = "短期型") String productType) {

        Sort sort = Sort.by("productType");
        Page<ProductInfo> products = managerService.getProductListByPage(pageNum,productType,dataCount, sort);

        return products;
    }

    /**
     * @Description: manager modify the products,inculde its name,productCode,price,and so on
     * @Author: @Evan
     * @CreateDate: 2018/8/5 21:57
     * @UpdateDate: 2018/8/5 21:57
     * @Version: 1.0
     */
    @RequestMapping(value = "/manager/modify/products", method = RequestMethod.POST)
    public boolean modifyProduct(ProductInfo product, MultipartFile file) {
//     Product product = new Product("ewfsdgsrhdfgxvadfgsfnxzdz", "200701", "中非让", 20.8, "稳", "这是一个", "http://8080", "2018-7-1", "2018-8-10", "2018-7-3", 1);
        boolean tag = false;
        try {
            tag = managerService.modifyProduct(product, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tag;
    }

    /**
     * @Description: manager add product
     * @Author: @Evan
     * @CreateDate: 2018/8/5 21:59
     * @UpdateDate: 2018/8/5 21:59
     * @Version: 1.0
     */
    @RequestMapping(value = "/manager/add/products", method = RequestMethod.POST)
    public Boolean addProduct(ProductInfo product, @RequestParam(value = "file") MultipartFile file) {
        // @RequestBody Product product Product product = new Product("ewfsdgsrhdfgxvadfgsfnxzdz", "200701", "中海", 20.8, "稳健型", "这是一个值得1", "http://8080", "2018-7-1", "2018-8-10", "2018-7-3", 1);
        //@RequestParam(value = "name") String name,@RequestParam(value = "price") double price, @RequestParam(value = "type") String type,@RequestParam(value = "description") String description,@RequestParam(value = "sellData") String sellData,@RequestParam(value = "deadline") String deadline,@RequestParam(value = "dueDate") String dueDate
        boolean tag = false;
        try {
            tag = managerService.addProduct(product, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tag;
    }

    /**
     * @Description: manager delete product by productCode,actually it is chance product's status
     * @Author: @Evan
     * @CreateDate: 2018/8/5 22:00
     * @UpdateDate: 2018/8/5 22:00
     * @Version: 1.0
     */
    @RequestMapping(value = "/manager/delete/products", method = RequestMethod.POST)
    @ResponseBody
    public String deleteProductByProductId(ProductInfo product) {
//        Product product = new Product("xdghhmbhnllllgcgxdf", "200871", "中海基金", 20.8, "稳健型", "这是一个值得1", "http://8080", "2018-7-1", "2018-8-10", "2018-7-3", 0);
        int n = 0;
        n = managerService.deleteProductByProductId(product);

        return "redirect:/manager/productlist";
    }

    @GetMapping("/get/productType")
    public List<ProductTypeInfo> getProductTypeList(){
        List<ProductTypeInfo> list=managerService.getProductType();
        return list;
    }
    @PostMapping("/manager/findProduct/byProductCodeOrProductName")
    public List<ProductInfo> findByProductCodeOrProductName(String productCode,String productName) {
        List<ProductInfo> productList = managerService.findByProductCodeOrProductName(productCode,productName);
        return productList;
    }

}
