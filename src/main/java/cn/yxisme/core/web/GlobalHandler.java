package cn.yxisme.core.web;

import cn.yxisme.core.common.ResultBean;
import cn.yxisme.core.exception.CodeMessageDef;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class GlobalHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    final static String SESSION_USER = "user";

    private HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getSession();
    }

    /**
     * 登入
     * @param user
     */
    protected void sessionLogin(User user) {
        logger.info("用户登录：【{}】", JSONObject.toJSONString(user));
        HttpSession session = getSession();
        session.setAttribute(SESSION_USER, user);
    }

    /**
     * 登出
     */
    protected void sessionLogout() {
        HttpSession session = getSession();
        Object user = session.getAttribute(SESSION_USER);
        if (user == null) {
            return;
        }

        logger.info("用户登出：【{}】", JSONObject.toJSONString(user));
        session.removeAttribute(SESSION_USER);
    }

    /**
     * 获取当前登录用户
     * @return
     * @throws MyException
     */
    public User getUser() throws MyException {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);

        if (user == null || user.getId() == 0) {
            throw new MyException(CodeMessageDef.USER_NOT_LOGGED_IN);
        }

        return user;
    }

    /**
     * 获取当前登录用户ID
     * @return
     * @throws MyException
     */
    public int getUserId() throws MyException {
        return getUser().getId();
    }

    /**
     * 全局处理异常
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected Object ExceptionHandler(Exception ex) {
        logger.error("", ex);
        ex.printStackTrace();

        if (ex instanceof MyException) {
            return new ResultBean((MyException) ex);
        }

        return ResultBean.systemError();
    }
}
