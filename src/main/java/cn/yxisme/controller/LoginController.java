package cn.yxisme.controller;

import cn.yxisme.context.Context;
import cn.yxisme.controller.bean.user.UserBean;
import cn.yxisme.core.common.ResultBean;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.core.web.GlobalHandler;
import cn.yxisme.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/login")
public class LoginController extends GlobalHandler {

    @Autowired
    Context context;

    /**
     * @param bean
     * @return ResultBean
     * @throws MyException
     */
    @PostMapping(value = "")
    public Object login(@RequestBody @Validated({UserBean.LoginValid.class}) UserBean bean) throws MyException {
        User userInDB = context.loginService.checkLogin(bean.getUsername(), bean.getPassword());
        sessionLogin(userInDB);
        return new ResultBean();
    }

    /**
     * 登出
     * @return ResultBean
     */
    @PostMapping(value = "/logout")
    public Object logout() {
        sessionLogout();
        return new ResultBean();
    }

    /**
     * 获取当前登录的用户
     * @return ResultBean
     */
    @GetMapping(value = "/getUser")
    public Object getSessionUser() throws MyException {
        User user = context.userService.get(getUserId());
        return new ResultBean(user);
    }

    @GetMapping(value = "/test")
    public Object test(@RequestParam @NotNull(message = "ID不能为空") Integer id) throws MyException {
        User user = context.userService.get(id);
        return new ResultBean(user);
    }
}
