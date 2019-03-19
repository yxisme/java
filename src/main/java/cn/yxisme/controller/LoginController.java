package cn.yxisme.controller;

import cn.yxisme.core.common.ResultBean;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.core.web.GlobalHandler;
import cn.yxisme.entity.User;
import cn.yxisme.service.LoginService;
import cn.yxisme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "true", value = "http://localhost:8081")
@RestController
@RequestMapping("/login")
public class LoginController extends GlobalHandler {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @return
     * @throws MyException
     */
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    public Object loginVerify(@RequestBody User user) throws MyException {
        User userInDB = loginService.login(user);
        sessionLogin(userInDB);
        return new ResultBean();
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Object logout() {
        sessionLogout();
        return new ResultBean();
    }

    /**
     * 获取当前登录的用户
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Object getSessionUser() throws MyException {
        User user = userService.get(getUserId());
        return new ResultBean(user);
    }
}
