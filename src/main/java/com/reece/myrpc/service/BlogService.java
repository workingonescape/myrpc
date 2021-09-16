package com.reece.myrpc.service;

import com.reece.myrpc.pojo.Blog;
import com.reece.myrpc.pojo.User;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 13:22
 * @description:
 */
public interface BlogService {

    Blog getById(Integer id);

    List<Blog> getAllUser(User user);
}
