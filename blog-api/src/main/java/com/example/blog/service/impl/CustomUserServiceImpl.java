package com.example.blog.service.impl;

import com.example.blog.entity.TUser;
import com.example.blog.mapper.TUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jackyfeng
 * @date
 * @apiNote
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    @Resource
    TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser tUser = tUserMapper.loadUserByUsername(s);
        if (s == null){ // 判断用户名是否存在
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return tUser;
    }
}