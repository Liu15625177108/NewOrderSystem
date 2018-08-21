package hsbc.groupthree.ordersystem.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName OrderInfo
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.order.entity
 * @Date 2018/8/16 23:17
 */
@Data
@Entity
public class OrderInfo {
    /**
     * orderid
     */
    @Id
    @JsonProperty(value = "orderid")
    private String orderId;
    /**
     * productname
     */
    @Column(nullable = true)
    @JsonProperty(value = "productname")
    private String productName;
    /**
     * productnumber
     */
    @Column(nullable = true)
    @JsonProperty(value = "productnumber")
    private int productNumber;
    /**
     * username
     */
    @Column(nullable = true)
    @JsonProperty(value = "username")
    private String userName;
    /**
     * userphone
     */
    @Column(nullable = true)
    @JsonProperty(value = "userphone")
    private String userPhone;
    /**
     * useraddress
     */
    @Column(nullable = true)
    @JsonProperty(value = "useraddress")
    private String userAddress;
    /**
     * one product price
     */
    @Column(nullable = true)
    @JsonProperty(value = "productprice")
    private double productPrice;
    /**
     * the status of order ，1 is open,2 is undetermined,0 is close
     */
    @Column(nullable = true)
    @JsonProperty(value = "orderstatus")
    private int orderStatus;
    /**
     * the time of to order
     */
    @Column(nullable = true)
    @JsonProperty(value = "starttime")
    private String startTime;
    /**
     * the time of to order
     */
    @Column(nullable = true)
    @JsonProperty(value = "productDuelate")
    private String productDuelate;
    /**
     * the totalmoney
     */
    @Column(nullable = true)
    @JsonProperty(value = "totalmoney")
    private double totalMoney;
    /**
     * the productCode
     */
    @Column(nullable = true)
    @JsonProperty(value = "productCode")
    private String productCode;

    public OrderInfo(){}

    public OrderInfo(String orderId, String productName, String userName,
                      String userPhone, String userAddress, int orderStatus,
                      String startTime,String productDuelate,double totalMoney,
                       String productCode ) {
        this.orderId = orderId;
        this.productName = productName;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.productDuelate=productDuelate;
        this.totalMoney = totalMoney;
        this.productCode=productCode;
    }
}
