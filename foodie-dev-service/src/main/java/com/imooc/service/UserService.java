
package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {
    /*
    判断用户名是否存在
     */
    boolean queryUserNameIsExist(String userName);
    /*
       创建用户
        */
    Users createUser(UserBO userBO);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Users queryUserForLogin(String username,String password);
}
