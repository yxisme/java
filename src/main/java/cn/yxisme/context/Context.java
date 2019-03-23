package cn.yxisme.context;

import cn.yxisme.service.LoginService;
import cn.yxisme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangxiong on 2019/3/23.
 */
public class Context {

    @Autowired
    public UserService userService;

    @Autowired
    public LoginService loginService;
}
