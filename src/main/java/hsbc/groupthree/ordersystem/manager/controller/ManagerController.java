package hsbc.groupthree.ordersystem.manager.controller;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.manager.service.ManagerService;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManagerController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.controller
 * @Date 2018/8/16 23:16
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    public ResultInfo login(@RequestParam(value = "workernum",required = true)String workerNum,
                            @RequestParam(value ="password" ,required = true)String passwod, HttpServletResponse httpServletResponse){

        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","Origin,X-Requespted-With,Content-Type,Accept");
                if(managerService.login(workerNum,passwod)==true) {
                    return managerService.findByworkerNum(workerNum);
                }
                return new ResultInfo(500,"fial",null);
    }

//    @RequestMapping("/order/list")
//    public List<ManagerInfo> orderList(){
//        List<ManagerInfo> managerInfoList=new ArrayList<ManagerInfo>();
//        ManagerInfo m1=new ManagerInfo();
//        m1.setWorkerNum("1233");
//        m1.setWorkerName("aa");
//        m1.setWorkerDepartment("dev");
//        for(int i=0;i<30;i++) {
//            managerInfoList.add(m1);
//        }
//        return  managerInfoList;
//     }

}
