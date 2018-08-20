package hsbc.groupthree.ordersystem.manager.service;
import hsbc.groupthree.ordersystem.commons.utils.CommonTool;
import hsbc.groupthree.ordersystem.commons.utils.DataCheckTool;
import hsbc.groupthree.ordersystem.commons.utils.DataUtils;
import hsbc.groupthree.ordersystem.commons.utils.UUIDUtils;
import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.manager.repository.ManagerLoginRepository;
import hsbc.groupthree.ordersystem.manager.repository.ManagerRepository;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductTypeInfo;
import hsbc.groupthree.ordersystem.product.repository.ProductTypeRepository;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.systemlog.entity.SystemLog;
import hsbc.groupthree.ordersystem.systemlog.repository.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManagerServiceImpl
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.service.Impl
 * @Date 2018/8/17 11:34
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    private final ProductTypeRepository productTypeRepository;
    private final SystemLogRepository systemLogRepository;
    private final ManagerRepository managerRepository;
    private final DataCheckTool dataCheckTool;
    CommonTool commonTool = new CommonTool();

    /**
     * the ManagerServiceImpl constructor  is to init managerRepository
     */
    @Autowired
    public ManagerServiceImpl(ProductTypeRepository productTypeRepository, ManagerRepository managerRepository, SystemLogRepository systemLogRepository, DataCheckTool dataCheckTool) {
        this.productTypeRepository = productTypeRepository;
        this.managerRepository = managerRepository;
        this.systemLogRepository = systemLogRepository;
        this.dataCheckTool = dataCheckTool;
    }
    @Autowired
    private ManagerLoginRepository managerLoginRepository;

    /**
     * @Description: get products
     * @Author: @Evan
     * @CreateDate: 2018/8/5 22:08
     * @UpdateDate: 2018/8/5 22:08
     * @Version: 1.0
     */
    @Override
    public Page<ProductInfo> getProductListByPage(int page, String productType, int count, Sort sort) {
        Specification<ProductInfo> specification = new Specification<ProductInfo>() {
            @Override
            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Path<Long> status = root.get("status");
                Predicate predicate = criteriaBuilder.equal(status, 1);
                predicates.add(predicate);
                Path<Long> path = root.get("productType");
                Predicate predicate1 = criteriaBuilder.equal(path, productType);
                predicates.add(predicate1);
                return criteriaBuilder.and(predicates
                        .toArray(new Predicate[] {}));
            }
        };

        Pageable pageable = PageRequest.of(page, count, sort);
        return managerRepository.findAll(specification, pageable);
    }

    /**
     * @Description: add products
     * @Author: @Evan
     * @CreateDate: 2018/8/5 22:08
     * @UpdateDate: 2018/8/5 22:08
     * @Version: 1.0
     */
    @Override
    public boolean addProduct(ProductInfo product, MultipartFile file) throws IOException {

        boolean tag = false;
        String flage = "0";
        String checkProductId="";
        String uploadFileName = "img/logo-symbol.png";
        /*check the data whether in a rule*/
            boolean checkDataBoolean = commonTool.checkData(product);
            if (checkDataBoolean) {
                /*check the Icon's format of .jpeg .png .gif and upload icon*/
                if (file!=null){
                    uploadFileName = dataCheckTool.checkIconAndUploadIcon(file);
                }
                if (!flage.equals(uploadFileName)) {
                    /*check the productCode of unique*/
                    List<ProductInfo> products = managerRepository.findByStatus(1);
                    tag = commonTool.checkUniqueOfProduct(product, products);
                    /*if the productCode is unique,allow add product*/
                    if (tag) {
                        /*save the production to database*/
                        if(checkProductId.equals(product.getId())){
                            product.setId(UUIDUtils.getUUID());
                        }
                        product.setIcon(uploadFileName);
                        managerRepository.save(product);
                    }
                }
        }

        return tag;
    }

    @Override
    public int deleteProductByProductId(ProductInfo product) {
        int n = 0;
        String msg = "delete product's of code = " + product.getProductCode()+"and product's id= "+product.getId();
        /*judge the of current time and product's dueDate,if current'time not in the rang of dueDate ,can't not delete product*/
        DataUtils dataUtils = new DataUtils();
        boolean tag = dataUtils.compareCurrentTimeAndDueDate(product);
        if (tag) {
            ProductInfo pro=managerRepository.findByid(product.getId());
            pro.setStatus(0);
            managerRepository.save(pro);
            /*if manager modify the production information,it should be recording*/
            SystemLog systemLog = new SystemLog();
            commonTool.setLog(systemLog);
            systemLog.setOperation(msg);
            systemLogRepository.save(systemLog);
            n = 1;
        }
        return n;
    }

    @Override
    public boolean modifyProduct(ProductInfo product, MultipartFile file) throws IOException {
        boolean tag = false;
        String msg = "";
        /**check the productCode unique*/
        ProductInfo compareProduct = managerRepository.findByid(product.getId());
        CommonTool commonTool = new CommonTool();
        boolean checkDataBoolean = commonTool.checkData(product);
        if (checkDataBoolean) {
            List<ProductInfo> products = managerRepository.findByStatus(1);
            msg = commonTool.getMessage(product, compareProduct);
            tag = commonTool.checkUniqueOfProduct(product, products);
            if (tag) {
                managerRepository.saveAndFlush(product);
                SystemLog systemLog = new SystemLog();
                commonTool.setLog(systemLog);
                systemLog.setOperation(msg);
                systemLogRepository.save(systemLog);
            }
        }
        return tag;
    }

    @Override
    public List<ProductTypeInfo> getProductType() {
        return productTypeRepository.findAll();
    }

    @Override
    public List<ProductInfo> findByProductCodeOrProductName(String productCode,String productName) {
        return managerRepository.findByProductCodeOrProductName(productCode,productName);
    }


    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter [workerNum, password]
     *@Date:16:46 2018/8/20
     *@Package: hsbc.groupthree.ordersystem.manager.service
     */
    @Override
    public boolean login(String workerNum, String password) {
        ManagerInfo managerInfo=managerLoginRepository.findOneByWorkerNum(workerNum);
        if(managerInfo!=null&&managerInfo.getLoginPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    /**
    *@Author Jerry.Liu
    *@Description://TODO
    *@Parameter [workerNum]
    *@Date:16:47 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findByworkerNum(String workerNum) {
        ManagerInfo managerInfo=new ManagerInfo();
        managerInfo=managerLoginRepository.findOneByWorkerNum(workerNum);
        return new ResultInfo(200,"success",managerInfo);
    }

    @Override
    /**
    *@Author Jerry.Liu
    *@Description://TODO
    *@Parameter []
    *@Date:16:47 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findAllOrder() {
        return null;
    }

    @Override
    /**
    *@Author Jerry.Liu
    *@Description://TODO
    *@Parameter []
    *@Date:16:47 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findOrderByUsername(String userName) {
        return null;
    }

    @Override
    /**
    *@Author Jerry.Liu
    *@Description://TODO
    *@Parameter []
    *@Date:16:47 2018/8/20
    *@Package: hsbc.groupthree.ordersystem.manager.service
    */
    public ResultInfo findOrderByDate(String date) {
        return null;
    }

}
