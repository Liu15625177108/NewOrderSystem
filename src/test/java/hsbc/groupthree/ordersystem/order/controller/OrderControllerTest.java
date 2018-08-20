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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OrderController.class)
@WebMvcTest(OrderController.class)
@Slf4j
public class OrderControllerTest {
    private DataUtils dataUtils=new DataUtils();
    private CommonsUtils commonsUtils=new CommonsUtils();
    @Autowired
    private MockMvc mvc;
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
        ProductInfo productInfo = new ProductInfo("11",
                100, "信用卡");
        OrderInfo orderInfo = new OrderInfo(commonsUtils.getUUID(),productInfo.getProductName(),
                 userInfo.getUsername(), userInfo.getPhone(), userInfo.getAddress(),
                 1, dataUtils.getCurrentTime(),productInfo.getProductPrice()
                 );
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
                .param("productCode","11")
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

    @Test
    public void testFindOrderById()throws Exception{

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setOrderId("A11");
        orderInfo.setOrderStatus(1);
        orderInfo.setProductName("龙卡VIP");
        orderInfo.setProductNumber(2);
        orderInfo.setProductPrice(6.66);
        orderInfo.setUserName("小鑫");
        orderInfo.setUserAddress("华南师范大学西三425");
        orderInfo.setStartTime("2015年");
        orderInfo.setUserPhone("1234这是电话4321");
        orderInfo.setTotalMoney(13.32);

        given(this.orderService.getOrderInfoByOrderId("A11")).willReturn(orderInfo);

        log.info(orderInfo.toString());

        String result = this.mvc.perform(get("/order/findorder?orderId=A11").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(10))
                //.andExpect(content().string("还没写"))
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void testShowAllOrderOfUser() throws Exception{

        OrderInfo orderInfo = new OrderInfo();
        OrderInfo orderInfo1 = new OrderInfo();
        OrderInfo orderInfo2 = new OrderInfo();

        List<OrderInfo> orderInfoList = new ArrayList<>();

        orderInfo.setOrderId("A11");
        orderInfo.setOrderStatus(1);
        orderInfo.setProductName("龙卡VIP");
        orderInfo.setProductNumber(2);
        orderInfo.setProductPrice(6.66);
        orderInfo.setUserName("小鑫");
        orderInfo.setUserAddress("华南师范大学西三425");
        orderInfo.setStartTime("2015年");
        orderInfo.setUserPhone("1234这是电话4321");
        orderInfo.setTotalMoney(13.32);

        orderInfo1.setOrderId("B11");
        orderInfo1.setOrderStatus(1);
        orderInfo1.setProductName("黑卡VIP");
        orderInfo1.setProductNumber(1);
        orderInfo1.setProductPrice(6.66);
        orderInfo1.setUserName("小鑫");
        orderInfo1.setUserAddress("华南师范大学西三425");
        orderInfo1.setStartTime("2015年");
        orderInfo1.setUserPhone("1234这是电话4321");
        orderInfo1.setTotalMoney(6.66);

        orderInfo2.setOrderId("C11");
        orderInfo2.setOrderStatus(1);
        orderInfo2.setProductName("Super VIP");
        orderInfo2.setProductNumber(2);
        orderInfo2.setProductPrice(6.66);
        orderInfo2.setUserName("小鑫");
        orderInfo2.setUserAddress("华南师范大学西三425");
        orderInfo2.setStartTime("2015年");
        orderInfo2.setUserPhone("1234这是电话4321");
        orderInfo2.setTotalMoney(13.32);

        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo1);
        orderInfoList.add(orderInfo2);

        given(this.orderService.findAllOrder("小鑫")).willReturn(orderInfoList);

        log.info(orderInfoList.toString());

        String result  = this.mvc.perform(get("/order/showuserorder?userName=小鑫").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string("还没写"))
                .andReturn().getResponse().getContentAsString();

        log.info(result);
    }


}