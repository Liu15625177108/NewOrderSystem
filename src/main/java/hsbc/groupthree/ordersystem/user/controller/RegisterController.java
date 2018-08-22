package hsbc.groupthree.ordersystem.user.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    public ResultView sendVerifyMessage(@RequestParam("phone-number") String phoneNumber, HttpServletResponse response) {
        String rand = SendSmsUtil.createRandNum();
        SendSmsUtil.sendMsgTo(phoneNumber, rand);
        Cookie cookie = new Cookie("code", SendSmsUtil.sigMD5(rand));
        response.addCookie(cookie);
        ResultView resultView = new ResultView();
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
    public ResultView verifyCode(@RequestParam("verifyCode") String code, HttpServletRequest request,
                                 HttpServletResponse response) {
        ResultView<String> resultView = new ResultView<>();
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
    public ResultView usernameCheck(@RequestParam("username") String username) {
        ResultView resultView = new ResultView();
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
     * @return ResultView
     * @Description input register message of user
     */
    @PostMapping("/login-message")
    public ResultView loginMessage(@RequestBody @Validated UserInfo userInfo, BindingResult bindingResult) {
        ResultView resultView = new ResultView();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            String err = "";
            for (ObjectError error : errors) {
                err += error.getDefaultMessage();
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



