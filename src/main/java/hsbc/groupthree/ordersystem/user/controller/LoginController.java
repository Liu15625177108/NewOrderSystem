package hsbc.groupthree.ordersystem.user.controller;

import hsbc.groupthree.ordersystem.commons.security.JwtTool;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.user.service.LoginServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @package : hsbc_team_3.ordersystem.loginregister
 * @program : ordersystem
 * @description : login api entrance
 * @author : Jeff.Li
 * @date : 2018年08月03日 10:29:15
 **/
@RestController
@RequestMapping("/user/login")
public class LoginController {

    private final LoginServicesImpl loginServices;

    private final JwtTool jwtTool;

    @Autowired
    public LoginController(LoginServicesImpl loginServices, JwtTool jwtTool) {
        this.loginServices = loginServices;
        this.jwtTool = jwtTool;
    }

    /**
     * @param username, password, request
     * @return ResultInfo
     * @description user login api
     */
    @GetMapping("/dologin")
    public ResultInfo login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        ResultInfo resultView = new ResultInfo();
        String mes = loginServices.loginJudge(username,password);
        resultView.setMsg(mes);
        if("OK".equals(mes)){
            resultView.setCode(200);
            response.setHeader("Authorization", "Bearer " +
                    jwtTool.generateToken(loginServices.getUserIdByUsername(username), username));
        } else {
            resultView.setCode(500);
        }
        return resultView;
    }

}
