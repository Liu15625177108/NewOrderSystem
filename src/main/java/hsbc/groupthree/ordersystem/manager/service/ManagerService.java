package hsbc.groupthree.ordersystem.manager.service;
import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


/**
 * @ClassName ManagerService
 * @Author:Jerry.Liu;
 * @Description://the interface of mananger's level,include  login,modify the product,check the order.
 * @Package hsbc.groupthree.ordersystem.manager.service
 * @Date 2018/8/16 23:15
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
     * @param product
     * @return boolean
     * @descripe:add product
     */
    boolean addProduct(ProductInfo product, MultipartFile file) throws IOException;


    /**
     * @param product
     * @return int 0:fail,1:success
     */
    int deleteProductByProductId(ProductInfo product);

    /**
     * @param product
     * @return
     * @Descript:it is modify the product's information
     */
    boolean modifyProduct(ProductInfo product, MultipartFile file) throws IOException;

    List<ProductTypeInfo> getProductType();

    /**
     * @param productCode
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductCodeOrProductName(String productCode, String productName);


    /**
    *@Author Jerry.Liu
    *@Description:the method is used to verified  whather the workernum  could match the password;
    *@Parameter [workerNum,password]
    *@Date:16:49 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public boolean login(String workerNum, String password);

    /**
    *@Author Jerry.Liu
    *@Description:if the manager 's information had verified ,it woould return  a resultInfo(code ,msg,ManegeInfo);
    *@Parameter [workerNum]
    *@Date:16:52 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findByworkerNum(String workerNum);

    /**
    *@Author Jerry.Liu
    *@Description:return all of the order
    *@Parameter
    *@Date:16:54 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findAllOrder();

    /**
    *@Author Jerry.Liu
    *@Description://return the orderlist of the userInfo named Username;
    *@Parameter [Username]
    *@Date:16:54 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findOrderByUsername(String userName);

    /**
    *@Author Jerry.Liu
    *@Description://return the orderlist of the Date;
    *@Parameter [date]
    *@Date:16:54 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findOrderByDate(String date);
}


