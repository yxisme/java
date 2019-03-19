package cn.yxisme.core.web;

import cn.yxisme.core.exception.CodeMessageDef;
import cn.yxisme.core.exception.MyException;
import cn.yxisme.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class GlobalHandler {

    public final static String SESSION_USER = "user";

    private HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 登入
     * @param user
     */
    public void sessionLogin(User user) {
        HttpSession session = getSession();
        session.setAttribute(SESSION_USER, user);
    }

    /**
     * 登出
     */
    public void sessionLogout() {
        HttpSession session = getSession();
        session.removeAttribute(SESSION_USER);
    }

    /**
     * 获取当前登录用户
     * @return
     * @throws MyException
     */
    public User getUser() throws MyException {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
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
        ex.printStackTrace();

        Map map = new HashMap();
        if (ex instanceof MyException) {
            MyException me = (MyException) ex;
            map.put("code", me.getCode());
            map.put("msg", me.getMsg());
            map.put("data", null);
        } else {
            map.put("code", CodeMessageDef.SYSTEM_ERROR.getCode());
            map.put("msg", CodeMessageDef.SYSTEM_ERROR.getMsg());
            map.put("data", null);
        }

        return map;
    }
}
