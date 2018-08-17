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
 * @description〈to impl resultview〉
 * @author Chen
 * @create 2018/8/13
 * @since 1.0.0
 */
@Service
public class ResultViewServiceImpl implements ResultViewService {

    /**
     * @Author Chen
     * @Description //TODO show resultView on the basi
     * @Date 11:29 2018/8/15
     * @Param [i]
     * @return hsbc.team03.ordersystem.toorder.result.ResultView
     **/
    @Override
    public ResultInfo ResultErrorView(int i) {
        if(i==27){
            ResultInfo resultView=new ResultInfo<String>(401,"error",ResultEnum.MONEY_NOT_ENOUGH.getMessage());
            return resultView;
        }else if(i==26){
            ResultInfo resultView=new ResultInfo<String>(401,"error",ResultEnum.PAY_PASSWORD_FAIL.getMessage());
            return resultView;
        }else if(i==28){
            ResultInfo resultView=new ResultInfo<String>(401,"error",ResultEnum.ORDER_CANCEL_FAIL.getMessage());
            return resultView;
        }else if(i==29){
            ResultInfo resultView=new ResultInfo<String>(401,"error",ResultEnum.SELECT_NOT_ENOUGH.getMessage());
            return resultView;
        }else if(i==14){
            ResultInfo resultView=new ResultInfo<String>(401,"error",ResultEnum.ORDER_STATUS_ERROR.getMessage());
            return resultView;
        }


        return null;
    }

    @Override
    public ResultInfo ResultSuccess(int i) {
        if(i==22){
            ResultInfo resultView=new ResultInfo<String>(200,"success",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
            return resultView;
        }else if(i==23){
            ResultInfo resultView=new ResultInfo<String>(200,"success",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
            return resultView;
        }
        return null;
    }
}