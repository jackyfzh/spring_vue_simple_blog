package com.example.blog.mapper;

import com.example.blog.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
public interface TUserMapper extends BaseMapper<TUser> {
    // 根据用户名查找人物
    TUser loadUserByUsername(String username);
}
