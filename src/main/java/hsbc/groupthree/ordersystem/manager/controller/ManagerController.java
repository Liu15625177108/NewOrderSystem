package hsbc.groupthree.ordersystem.manager.controller;

import hsbc.groupthree.ordersystem.manager.service.ManagerService;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
        return null;
    }

}
