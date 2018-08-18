package hsbc.groupthree.ordersystem.order.controller;
import hsbc.groupthree.ordersystem.commons.utils.CommonsUtils;
import hsbc.groupthree.ordersystem.commons.utils.DataUtils;
import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.order.service.OrderService;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.product.service.ProductService;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OrderController.class)
@WebMvcTest(OrderController.class)
@Slf4j
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    
    private CommonsUtils commonsUtils=new CommonsUtils();
    @MockBean
    private OrderService orderService;
    @MockBean
    private UserService userService;
    @MockBean
    private ProductService productService;

    @Test
    public void testToOrder() throws Exception {

        UserInfo userInfo = new UserInfo(commonsUtils.getUUID(), "Chen",
                100000, "123", "11111111111",
                "岗顶");
        ProductInfo productInfo = new ProductInfo("11", 3,
                100, "信用卡");
        OrderInfo orderInfo = new OrderInfo(commonsUtils.getUUID(),
                productInfo.getProductName(), productInfo.getProductNumber(),
                userInfo.getUserName(), userInfo.getUserPhone(),
                userInfo.getUserAddress(), productInfo.getProductPrice(),
                1,DataUtils.getCurrentTime(),productInfo.getProductNumber() *
                productInfo.getProductPrice());
        ResultInfo resultView = new ResultInfo<OrderInfo>(200, "success", orderInfo);


        given(this.userService.toValidatePayPassword(Mockito.any(UserInfo.class), eq("123"))).willReturn(true);
        given(this.productService.getProductInfoByProductId(eq("11"))).willReturn(productInfo);
        given(this.orderService.insertOrder(Mockito.any(ProductInfo.class), Mockito.any(UserInfo.class))).willReturn(true);
        given(this.userService.toValidateMoney(Mockito.any(UserInfo.class),Mockito.any(ProductInfo.class))).willReturn(true);
        given(this.orderService.getOrderPrice(Mockito.any(ProductInfo.class))).willReturn(300.0);
        given(this.userService.getUserInfoByUserId(eq("111"))).willReturn(userInfo);


//        String jsonString = "{\"productid\":\"17e5372f-dfc1-41e7-b37a-a1edae87d299\"," +
//                "\"productnumber\":3,\"productprice\":100.0,\"productname\":\"信用卡\"}";


        String result = this.mvc.perform(post("/order/toorder")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(jsonString)
                .requestAttr("userId", "111")
//                .sessionAttr("userId", "111")
                .param("productId","11")
                .param("payPassword", "123"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(); //return response's value
        System.out.println(result);
        log.info(result);
    }

    @Test
    public void cancelOrder() throws Exception {
    /*    String beginTime=new String("2018-08-7 10:22:22");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date bt=sdf.parse(beginTime);
        OrdersInfo orderInfo=new OrdersInfo("01","信用卡",
                12,"Chen","12313212312",
                "岗顶",1000.0,1,bt);
        
        given(this.orderService.getOrderInfoByOrderId(eq("123"))).willReturn(orderInfo);*/
        given(this.orderService.determineTime(eq("01"))).willReturn(true);
        given(this.orderService.updateOrderStatus(eq("01"))).willReturn(true);

        String result = this.mvc.perform(post("/order/tocancelorder")
                .param("orderId", "01"))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}