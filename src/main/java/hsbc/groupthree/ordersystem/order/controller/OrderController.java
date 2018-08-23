package hsbc.groupthree.ordersystem.order.controller;

import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.order.service.OrderService;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.service.ProductService;
import hsbc.groupthree.ordersystem.result.ResultViewService;
import hsbc.groupthree.ordersystem.result.ResultViewServiceImpl;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;


/**
 * @ClassName OrderController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.controller
 * @Date 2018/8/16 23:16
 */
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductService productsService;
//    @Autowired
//    private ResultViewService resultViewService;

    private ResultViewService resultViewService = new ResultViewServiceImpl();


    /**
     * @return java.lang.Object
     * @Author Chen
     * @Description //TODO  To place an order
     * @Date 5:52 2018/8/3
     * @Param []
     **/
    @PostMapping(value = "/toorder")
    public @ResponseBody
    Object toOrder(@RequestParam("productCode") String productCode, @RequestParam("payPassword") String payPassword, HttpServletResponse response, HttpServletRequest request) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        System.out.println(payPassword);
        ProductInfo productInfo = productsService.getProductInfoByProductCode(productCode);
        String userId = (String) request.getAttribute("userId");
        System.out.println(userId);
        UserInfo userInfo = userService.getUserInfoByUserId("11");
        if (userInfo != null) {
            System.out.println(userInfo.getUsername() + "++");
            System.out.println(productInfo.getProductName());
        }
        if(payPassword!=null&&!payPassword.equals("")) {
            //to check userPayPassword
            if (userService.toValidatePayPassword(userInfo, payPassword)) {
                //To compare userMoney and orderPrice
                if ( userService.toValidateMoney(userInfo, productInfo)) {
                    if (orderService.insertOrder(productInfo, userInfo)) {
                        return resultViewService.ResultSuccess(23);
                    }
                    return resultViewService.ResultErrorView(14);
                }
                return resultViewService.ResultErrorView(27);
            }
            return resultViewService.ResultErrorView(26);
        }
        return resultViewService.ResultErrorView(30);
        }



    /**
     * @return java.lang.Object
     * @Author Chen
     * @Description //TODO to cancel order
     * @Date 15:28 2018/8/4
     * @Param [orderId]
     **/
    @PostMapping(value = "/tocancelorder")
    public @ResponseBody
    Object toCancelOrder(@RequestParam("orderId") String orderId, @RequestParam("payPassword") String payPassword, HttpServletResponse response, HttpServletRequest request) throws ParseException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        String userId = (String) request.getAttribute("userId");
        System.out.println(userId);
        UserInfo userInfo = userService.getUserInfoByUserId("11");
        if (orderId != null && !orderId.equals("")) {
            if(payPassword!=null&&!payPassword.equals("")) {
                if (userService.toValidatePayPassword(userInfo, payPassword)) {
                    return orderService.updateOrderStatus(orderId);
                }
                return resultViewService.ResultErrorView(26);
            }
            return resultViewService.ResultErrorView(30);
        }
        return resultViewService.ResultErrorView(29);
    }


    /**
     * @Method findOrderById
     * @Description //find order by orderId, it must be null or one
     * @Author Alan Ruan
     * @Date 2018/08/20 11:51:55
     * @Param [orderId]
     * @Return java.lang.Object
     */
    @GetMapping(value = "/findorder")
    public Object findOrderById(@RequestParam (value = "orderId",defaultValue = "A11")
                                   String orderId, HttpServletResponse response,HttpServletRequest request){

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (orderId != null){
            OrderInfo orderInfo = orderService.getOrderInfoByOrderId(orderId);
            System.out.println(orderService.getOrderInfoByOrderId(orderId).toString());
            if (orderInfo != null){
                return orderInfo;
            }
            return null;
        }else {
            return resultViewService.ResultErrorView(33);
        }
    }

    /**
     * @Method showAllOrderOfUser
     * @Description //find order by username [list]
     * @Author Alan Ruan
     * @Date 2018/08/20 12:05:06
     * @Param [userId]
     * @Return java.lang.Object
     */
    @GetMapping(value = "/showuserorder")
    public List<OrderInfo> showAllOrderOfUser(@RequestParam(value = "userName",defaultValue = "小鑫")
                                               String userName, HttpServletResponse response, HttpServletRequest request){

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (userName != null){
            List<OrderInfo> list = orderService.findAllOrder(userName);
            System.out.println(orderService.findAllOrder(userName).toString());
            if (list != null){
                return list;
            }
        }
        return null;
//        return resultViewService.ResultErrorView(34);
    }


    /**
     * @Method findOrderByDate
     * @Description //  find order by order-start-time[list]
     * @Author Alan Ruan
     * @Date 2018/08/21 17:02:35
     * @Param [startTime, response, request]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @GetMapping("/findorderbydate")
    public List<OrderInfo> findOrderByDate(@RequestParam(value = "startTime",defaultValue = "20180821")String startTime,
                                           HttpServletResponse response, HttpServletRequest request){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (startTime != null){
            List<OrderInfo> list = orderService.findOrderByDate(startTime);
            System.out.println(orderService.findOrderByDate(startTime));
            if (list != null){
                return list;
            }
        }
        return null;
    }

    /**
     * @Method findOrderByProductSellDate
     * @Description //find order by productSellDate[list]
     * @Author Alan Ruan
     * @Date 2018/08/23 16:56:50
     * @Param [productSelldate, request, response]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @GetMapping("/findorderbyproductselldate")
    public List<OrderInfo> findOrderByProductSellDate(@RequestParam(value = "productSelldate",defaultValue = "20180822")
                                                      String productSelldate,HttpServletRequest request,HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (productSelldate != null){
            List<OrderInfo> list = orderService.findOrderByProductSellDate(productSelldate);
            if (list != null){
                return list;
            }
        }
        return null;
    }


    /**
     * @Method findOrderByProductDuelate
     * @Description // find order by productDuelate[List]
     * @Author Alan Ruan
     * @Date 2018/08/23 17:03:48
     * @Param [productDuelate, response, request]
     * @Return java.util.List<hsbc.groupthree.ordersystem.order.entity.OrderInfo>
     */
    @GetMapping("/findorderbyproductduelate")
    public List<OrderInfo> findOrderByProductDuelate(@RequestParam(value = "productDuelate",defaultValue = "20180822")
                                                     String productDuelate, HttpServletResponse response, HttpServletRequest request){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (productDuelate != null){
            List<OrderInfo> list = orderService.findOrderByProductDuelate(productDuelate);
            if (list != null){
                return list;
            }
        }
        return null;
    }


    /**
     * @Method show
     * @Description //    this is just a test , remember to delete it
     * @Author Alan Ruan
     * @Date 2018/08/21 16:50:34
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping("/sayhello")
    public  @ResponseBody
    String show(){
        return  "hello";
    }
}
