package cn.yxisme.service;

import cn.yxisme.core.exception.MyException;
import cn.yxisme.entity.User;

/**
 * Created by yangxiong on 2019/3/19.
 */
public interface UserService {
    User get(Integer userId) throws MyException;
}
