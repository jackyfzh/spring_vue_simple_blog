package com.example.blog.service;

import com.example.blog.entity.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.util.RespBean;

import java.util.HashMap;

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
    // 添加博客和标签
    RespBean saveBT(HashMap<String, Object> params);
    // 暂时保存博客
    RespBean temporarySave(HashMap<String, Object> params);
    // 更新博客
    RespBean updateBlog(HashMap<String,Object> params);
    // 逻辑删除博客
    RespBean logicDeleteBlog(String id);
    // 彻底删除博客和标签
    RespBean deleteBT(String id);
    // 还原博客
    RespBean recoveryBlog(String id);
    // 根据博客id查询
    RespBean getByBlogId(String id);
}
