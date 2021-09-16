package com.reece.myrpc.service.impl;

import com.reece.myrpc.pojo.Blog;
import com.reece.myrpc.pojo.User;
import com.reece.myrpc.service.BlogService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 14:19
 * @description:
 */
public class BlogServiceImpl implements BlogService {


    @Override
    public Blog getById(Integer id) {
        return Blog.builder().id(123).content("ddfsdfsd").title("test").user(User.builder().id(546).name("user").sex(1).build()).build();
    }

    @Override
    public List<Blog> getAllUser(User user) {

        List<Blog> blogs = new ArrayList<>();

        Blog blog1 = Blog.builder().id(1231).content("ddfsdfsd").title("test").user(User.builder().id(546).name("user").sex(1).build()).build();
        Blog blog2 = Blog.builder().id(1232).content("ddfsdfsd").title("test").user(User.builder().id(546).name("user").sex(1).build()).build();
        Blog blog3 = Blog.builder().id(1233).content("ddfsdfsd").title("test").user(User.builder().id(546).name("user").sex(1).build()).build();

        blogs.add(blog3);
        blogs.add(blog2);
        blogs.add(blog1);

        return blogs;
    }
}
