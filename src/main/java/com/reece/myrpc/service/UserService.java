package com.reece.myrpc.service;

import com.reece.myrpc.pojo.User;

/**
 * @author reece
 * @date 2021-09-06 8:40
 * @description:
 */
public interface UserService {



    User getUserById(Integer id);


    Boolean save(User user);

}
