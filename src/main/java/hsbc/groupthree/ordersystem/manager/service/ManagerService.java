package hsbc.groupthree.ordersystem.manager.service;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * @Author:Evan
 * @Date:2018/8/2 14:46
 * @Describe：
 * @Return:  products
 * @Param:
 */

public interface ManagerService {
    /**this is manager to get all the products*/
    /**
     * @param page
     * @param productType
     * @param count
     * @param sort
     * @return
     */
    Page<ProductInfo> getProductListByPage(int page, String productType, int count, Sort sort);

    /**
     * @descripe:add product
     * @param product
     * @return boolean
     *
     */
    boolean addProduct(ProductInfo product, MultipartFile file) throws IOException;

    /**
     * @param product
     * @return int 0:fail,1:success
     */
    int deleteProductByProductId(ProductInfo product);
    /**
     * @Descript:it is modify the product's information
     * @param product
     * @return
     */
   boolean modifyProduct(ProductInfo product, MultipartFile file) throws IOException;

    List<ProductTypeInfo> getProductType();

    /**
     *
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode,String productName);



}
