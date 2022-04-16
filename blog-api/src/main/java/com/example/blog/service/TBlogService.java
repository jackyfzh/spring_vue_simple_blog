package com.example.blog.service;

import com.example.blog.entity.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.util.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
public interface TBlogService extends IService<TBlog> {
    // 分页实现
    RespBean pageBlogs(Long current, Long limit, Boolean published, String flag, Boolean share_statement, Boolean is_delete);
    // 标题搜索
    RespBean getByTitle(String title);
}
