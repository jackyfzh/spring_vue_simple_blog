package com.example.blog.controller;


import com.example.blog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {

    // 获取数据库
    @Autowired
    TUserService tUserService;

    @GetMapping("/{id}") // 路由配置
    public Object test(@PathVariable("id") Long id) {
        return tUserService.getById(id);
    }

}