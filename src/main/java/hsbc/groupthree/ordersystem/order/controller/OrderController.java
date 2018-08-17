package hsbc.groupthree.ordersystem.order.controller;

import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName OrderController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.controller
 * @Date 2018/8/16 23:16
 */
@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrdersService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ResultViewService resultViewService;
//    private ResultViewService resultViewService = new ResultViewServiceImpl();

//    @GetMapping(value = "/toshoworder")
//    public Object showOrder(@RequestParam("productId")String productId){
//        
//    }

    /**
     * @return java.lang.Object
     * @Author Chen
     * @Description //TODO  To place an order
     * @Date 5:52 2018/8/3
     * @Param []
     **/
    @PostMapping(value = "/toorder")
    public @ResponseBody
    Object toOrder(@RequestParam("productId")String productId, @RequestParam("payPassword") String payPassword, HttpServletResponse response, HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        System.out.println(payPassword);
        ProductInfo productInfo = productsService.getProductInfoByProductId(productId);
        String userId = (String) request.getAttribute("userId");
        System.out.println(userId);
        UserInfo userInfo = userService.getUserInfoByUserId("11");
        if(userInfo!=null){
            System.out.println(userInfo.getUserName()+"++");
            System.out.println(productInfo.getProductName());
        }
        //To compare userMoney and orderPrice
        if (userService.toValidateMoney(userInfo,productInfo)) {
            //to check userPayPassword
            if (userService.toValidatePayPassword(userInfo, payPassword)) {
                if (orderService.insertOrder(productInfo, userInfo)) {
                    return resultViewService.ResultSuccess(23);
                }
                return resultViewService.ResultErrorView(14);
            }
            return resultViewService.ResultErrorView(26);
        }
        return resultViewService.ResultErrorView(27);
    }

    /**
     * @Author Chen
     * @Description //TODO test method
     * @Date 15:29 2018/8/14
     * @Param []
     * @return java.lang.Object
     **/
    @GetMapping(value = "/t1")
    public @ResponseBody
    Object test1() {
        TestInfo testInfo = new TestInfo();
        testInfo.setTestId(11);
        testInfo.setCode(1);
        return null;
    }

    /**
     * @Author Chen
     * @Description //TODO to cancel order
     * @Date 15:28 2018/8/4
     * @Param [orderId]
     * @return java.lang.Object
     **/
    @PostMapping(value = "/tocancelorder")
    public @ResponseBody
    Object toCancelOrder(@RequestParam("orderId") String orderId,HttpServletResponse response,HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        if (orderId != null && !orderId.equals("")) {
            if (orderService.determineTime(orderId)) {
                orderService.updateOrderStatus(orderId);
                return resultViewService.ResultSuccess(22);
            }
            return resultViewService.ResultErrorView(28);
        }
        return resultViewService.ResultErrorView(29);
    }
}
