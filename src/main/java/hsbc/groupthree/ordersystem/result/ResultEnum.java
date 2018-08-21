package hsbc.groupthree.ordersystem.result;

import lombok.Getter;

/**
 * @program: result code
 * @Description: To show the message of result according to the code
 * @author: Chen
 * @date: 2018/8/1 上午5:10
 */

@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),

    PARAM_ERROR(1, "incorrect parameter"),

    PRODUCT_NOT_EXIST(10, "Product does not exist"),

    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINISH_SUCCESS(23, "订单完结成功"),

    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(26, "登出成功"),

    PAY_PASSWORD_FAIL(26,"支付密码错误"),

    MONEY_NOT_ENOUGH(27,"钱不足"),

    ORDER_CANCEL_FAIL(28,"订单取消失败,超过七天"),

    SELECT_NOT_ENOUGH(29,"至少选择一项"),


    //Alan add some status below
    ORDER_SHOW_SUCCESS(30, "订单展示如图示"),

    LACK_OF_ORDERID(31,"没有提供订单号"),

    LACK_OF_USERNAME(32, "没有提供用户名"),

    ORDERID_REQUIRED(33,"请提供订单号"),

    USERNAME_REQUIRED(34, "请提供用户名"),




    PAY_PASSWORD_NULL(30,"支付密码不能为空"),

    CANCEL_REDUCE_30MONEY(300,"扣除30%的钱"),

    CANCEL_REDUCE_25MONEY(250,"扣除25%的钱"),

    CANCEL_REDUCE_20MONEY(200,"扣除20%的钱"),

    CANCEL_REDUCE_15MONEY(150,"扣除15%的钱"),

    CANCEL_REDUCE_10MONEY(100,"扣除10%的钱"),

    CANCEL_REDUCE_5MONEY(50,"扣除5%的钱")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
