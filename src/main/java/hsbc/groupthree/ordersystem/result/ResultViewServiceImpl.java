/**
 * Copyright (C), 2018-2018, CLPS
 * FileName: ResultViewServiceImpl
 * Author:   ca
 * Date:     2018/8/13 10:47
 * Description: to impl resultview
 * History:
 * <author>          <time>          <version>          <desc>
 * Chen          2018/8/13 10:47     1.0              to impl resultview
 */
package hsbc.groupthree.ordersystem.result;


import org.springframework.stereotype.Service;

/**
 * @author Chen
 * @description〈to impl resultview〉
 * @create 2018/8/13
 * @since 1.0.0
 */
@Service
public class ResultViewServiceImpl implements ResultViewService {

    private ResultInfo resultView;
    /**
     * @return hsbc.team03.ordersystem.toorder.result.ResultView
     * @Author Chen
     * @Description //TODO show resultView on the basi
     * @Date 11:29 2018/8/15
     * @Param [i]
     **/
    @Override
    public ResultInfo ResultErrorView(int i) {

        switch(i){
            case 27:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.MONEY_NOT_ENOUGH.getMessage());
                return resultView;
            case 26:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.PAY_PASSWORD_FAIL.getMessage());
                return resultView;
            case 28:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDER_CANCEL_FAIL.getMessage());
                return resultView;
            case 29:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.SELECT_NOT_ENOUGH.getMessage());
                return resultView;
            case 14:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDER_STATUS_ERROR.getMessage());
                return resultView;

            //Alan add some status below
            case 13:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
                return resultView;
            case 12:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDER_NOT_EXIST.getMessage());
                return resultView;
            case 16:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDER_DETAIL_EMPTY.getMessage());
                return resultView;
            case 19:
                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDER_OWNER_ERROR.getMessage());
                return resultView;
            case 31:
//                resultView = new ResultInfo<String>(401, "error", ResultEnum.LACK_OF_ORDERID.getMessage());
//                return resultView;
//            case 32:
//                resultView = new ResultInfo<String>(401, "error", ResultEnum.LACK_OF_USERNAME.getMessage());
//                return resultView;
//            case 33:
//                resultView = new ResultInfo<String>(401, "error", ResultEnum.ORDERID_REQUIRED.getMessage());
//                return resultView;
//            case 34:
//                resultView = new ResultInfo<String>(401, "error", ResultEnum.USERNAME_REQUIRED.getMessage());
//                return resultView;
            default:
                return  null;
        }

    }

    @Override
    public ResultInfo ResultSuccess(int i) {

        switch (i) {
            case 22:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
                return resultView;
            case 23:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
                return resultView;
            case 300:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_30MONEY.getMessage());
                return resultView;
            case 250:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_25MONEY.getMessage());
                return resultView;
            case 200:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_20MONEY.getMessage());
                return resultView;
            case 150:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_15MONEY.getMessage());
                return resultView;
            case 100:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_10MONEY.getMessage());
                return resultView;
            case 50:
                resultView = new ResultInfo<String>(200, "success", ResultEnum.CANCEL_REDUCE_5MONEY.getMessage());
                return resultView;

            //Alan add some status below
//            case 30:
//                resultView = new ResultInfo<String>(200, "success", ResultEnum.ORDER_SHOW_SUCCESS.getMessage());
//                return resultView;
            default:
                return null;
        }
    }
}