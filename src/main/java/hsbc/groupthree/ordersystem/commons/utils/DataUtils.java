package hsbc.groupthree.ordersystem.commons.utils;
import hsbc.groupthree.ordersystem.order.entity.OrderInfo;
import hsbc.groupthree.ordersystem.product.entity.ProductInfo;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.result.ResultViewService;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author:Evan
 * @Date:2018/8/4 10:23
 * @Describe：
 * @Return:
 * @Param:
 */
@Component
public class DataUtils {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ResultViewService resultViewService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Format time
     */
    public Date formatTime(String time) {
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public boolean compareCurrentTimeAndDueDate(ProductInfo product) {
        boolean tag = true;
        /*当前时间*/
        String currentTime = sdf.format(new Date());
        Date time1 = formatTime(currentTime);
        /*产品的出售到期时间*/
        Date time2 = formatTime(product.getProductDuedate());
        /*如果当前时间比产品出售的截止时间小，那么该产品不能删除，则返回false*/
        if (time1.getTime()-time2.getTime()<=0) {
            tag = false;
        }
        return tag;
    }


    /**
     * @return String Time
     * @method
     * @author @Evan
     * @version
     * @date 2018/8/6 14:07
     **/


    public  String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String time = sdf.format(new Date());
        return time;
    }

    public  Date getSystemTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new Date();
    }


    public ResultInfo reduceMoneyByTime(OrderInfo orderInfo) throws ParseException {
        //get userinfo by username in order to rollback money
        UserInfo userInfo = userRepository.findOneByUsername(orderInfo.getUserName());
        double nowMoney = userInfo.getBalance();
        //time determine
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(orderInfo.getProductSelldate());
        //get current time
        Date nowDate = new Date();
        //get before time
        Date after15Dates = new Date(date.getTime() + (long) 15 * 24 * 60 * 60 * 1000);
        Date after30Dates = new Date(date.getTime() + (long) 30 * 24 * 60 * 60 * 1000);
        Date after45Dates = new Date(date.getTime() + (long) 45 * 24 * 60 * 60 * 1000);
        Date after60Dates = new Date(date.getTime() + (long) 60 * 24 * 60 * 60 * 1000);
        Date after75Dates = new Date(date.getTime() + (long) 75 * 24 * 60 * 60 * 1000);

        if (nowDate.before(after15Dates)||nowDate.equals(after15Dates)) {
            // reduce 30%
            double reduceMoney=orderInfo.getTotalMoney()*0.30;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(300);
        }else if(nowDate.before(after30Dates)||nowDate.equals(after30Dates)){
            // reduce 25%
            double reduceMoney=orderInfo.getTotalMoney()*0.25;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(250);
        }else if(nowDate.before(after45Dates)||nowDate.equals(after45Dates)){
            //reduce 20%
            double reduceMoney=orderInfo.getTotalMoney()*0.20;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(200);
        }else if(nowDate.before(after60Dates)||nowDate.equals(after60Dates)){
            //reduce 15%
            double reduceMoney=orderInfo.getTotalMoney()*0.15;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(150);
        }else if(nowDate.before(after75Dates)||nowDate.equals(after75Dates)){
            //reduce 10%
            double reduceMoney=orderInfo.getTotalMoney()*0.10;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(100);
        }else{
            //reduce 5%
            double reduceMoney=orderInfo.getTotalMoney()*0.05;
            userInfo.setBalance(nowMoney + reduceMoney);
            userRepository.save(userInfo);
            return resultViewService.ResultSuccess(50);
        }
    }


}
