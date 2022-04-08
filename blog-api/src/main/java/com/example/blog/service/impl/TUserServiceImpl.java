package com.example.blog.service.impl;

import com.example.blog.entity.TUser;
import com.example.blog.mapper.TUserMapper;
import com.example.blog.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
