package hsbc.groupthree.ordersystem.user.controller;


import hsbc.groupthree.ordersystem.result.ResultInfo;
import hsbc.groupthree.ordersystem.user.entity.UserInfo;
import hsbc.groupthree.ordersystem.user.service.RegisterServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Package: hsbc_team_3.ordersystem.loginregister
 * @Program: ordersystem
 * @Description: register api entrance
 * @Author: Jeff.Li
 * @Created: 2018年08月02日 16:15:45
 **/
@RestController
@RequestMapping("/user/register")
public class RegisterController {

    private RegisterServicesImpl registerServices;

    @Autowired
    public RegisterController(RegisterServicesImpl registerServices) {
        this.registerServices = registerServices;
    }

    /**
     * @param : phoneNumber, response
     * @return void
     * @Description send message to phone
     */
    @GetMapping("/phone-number")
    public ResultInfo sendVerifyMessage(@RequestParam("phone-number") String phoneNumber, HttpServletResponse response) {
        String rand = SendSmsUtil.createRandNum();
        SendSmsUtil.sendMsgTo(phoneNumber, rand);
        Cookie cookie = new Cookie("code", SendSmsUtil.sigMD5(rand));
        response.addCookie(cookie);
        ResultInfo resultView = new ResultInfo();
        resultView.setCode(200);
        resultView.setMsg("message sent");

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept");

        return resultView;
    }

    /**
     * @param :code, request
     * @return java.util.HashMap<java.lang.String,java.lang.Boolean>
     * @Description verify the code from user
     */
    @GetMapping("/verify-code")
    public ResultInfo verifyCode(@RequestParam("verifyCode") String code, HttpServletRequest request,
                                 HttpServletResponse response) {
        ResultInfo<String> resultView = new ResultInfo<>();
        String aName = "code";
        String codes = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(aName)) {
                codes = c.getValue();
            }
        }
        if (codes != null && SendSmsUtil.sigMD5(code).equals(codes)) {
            resultView.setCode(200);
            resultView.setMsg("code confirm");
            return resultView;
        } else {
            resultView.setCode(500);
            resultView.setMsg("fail");
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept");
        return resultView;
    }

    /**
     * @param :username
     * @return java.util.HashMap<java.lang.String,java.lang.Boolean>
     * @Description check if the username available
     */
    @GetMapping("/username-check")
    public ResultInfo usernameCheck(@RequestParam("username") String username) {
        ResultInfo resultView = new ResultInfo();
        UserInfo list = registerServices.findUserByUsername(username);
        if (list == null) {
            resultView.setCode(200);
            resultView.setMsg("username available");
        } else {
            resultView.setCode(500);
            resultView.setMsg("username non-available");
        }
        return resultView;
    }


    /**
     * @param :userInfo, request
     * @return ResultInfo
     * @Description input register message of user
     */
    @PostMapping("/login-message")
    public ResultInfo loginMessage(@RequestBody @Validated UserInfo userInfo, BindingResult bindingResult) {
        ResultInfo resultView = new ResultInfo();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            String err = "";
            for (ObjectError error : errors) {
                err += error.toString();
            }
            resultView.setCode(500);
            resultView.setMsg("information failed" + err);
        } else if (registerServices.addUser(userInfo)) {
            resultView.setCode(200);
            resultView.setMsg("register success");
        } else {
            resultView.setCode(500);
            resultView.setMsg("register fail");
        }
        return resultView;
    }

}


