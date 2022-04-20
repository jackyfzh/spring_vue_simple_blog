package com.example.blog.mapper;

import com.example.blog.entity.TBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
public interface TBlogMapper extends BaseMapper<TBlog> {
    // 根据id查找博客
    TBlog getByBlogId(@Param("id") String id);
}
