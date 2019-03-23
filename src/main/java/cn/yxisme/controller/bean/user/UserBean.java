package cn.yxisme.controller.bean.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by yangxiong on 2019/3/23.
 * 使用Java Bean Validation做参数校验
 */
public class UserBean {

    @NotNull(groups = EditValid.class)
    private Integer id;
    @NotBlank(groups = {LoginValid.class, EditValid.class, AddValid.class})
    private String username;
    @NotBlank(groups = {LoginValid.class, EditValid.class, AddValid.class})
    private String password;

    // 分组校验
    public interface AddValid {}
    public interface EditValid {}
    public interface LoginValid {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
