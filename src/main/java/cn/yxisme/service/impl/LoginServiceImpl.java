package cn.yxisme.service.impl;

import cn.yxisme.core.exception.CodeMessageDef;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.dao.UserMapper;
import cn.yxisme.entity.User;
import cn.yxisme.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User checkLogin(String username, String password) throws MyException {
        User userInDB = userMapper.selectByUserName(username);
        if (userInDB == null) {
            throw new MyException(CodeMessageDef.USER_NAME_ERROR);
        }

        if (StringUtils.isEmpty(userInDB.getPassword())
                || !userInDB.getPassword().equals(password)) {
            throw new MyException(CodeMessageDef.USER_PASSWORD_ERROR);
        }

        return userInDB;
    }
}
