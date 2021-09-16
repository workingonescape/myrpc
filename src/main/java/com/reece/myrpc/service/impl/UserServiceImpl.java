package com.reece.myrpc.service.impl;

import com.reece.myrpc.pojo.User;
import com.reece.myrpc.service.UserService;

/**
 * @author reece
 * @date 2021-09-06 8:41
 * @description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("传入的id:" + id);
        User user = User.builder().name("id").id(id).sex(1).build();
        return user;
    }

    @Override
    public Boolean save(User user) {
        System.out.println("插入数据成功");
        return Boolean.TRUE;
    }

}
