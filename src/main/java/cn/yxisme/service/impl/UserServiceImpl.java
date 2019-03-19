package cn.yxisme.service.impl;

import cn.yxisme.core.exception.CodeMessageDef;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.dao.UserMapper;
import cn.yxisme.entity.User;
import cn.yxisme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiong on 2019/3/19.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User get(Integer userId) throws MyException {
        if (userId == null) {
            throw new MyException(CodeMessageDef.PARAMETER_ERROR);
        }
        return userMapper.selectByPrimaryKey(userId);
    }
}
