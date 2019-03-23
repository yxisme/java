package cn.yxisme.service;

import cn.yxisme.core.exception.MyException;
import cn.yxisme.entity.User;

public interface LoginService {
    User checkLogin(String username, String password) throws MyException;
}
