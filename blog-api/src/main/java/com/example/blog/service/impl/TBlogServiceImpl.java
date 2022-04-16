package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.TBlog;
import com.example.blog.mapper.TBlogMapper;
import com.example.blog.service.TBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@Service
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements TBlogService {

    @Autowired
    TBlogMapper tBlogMapper;
    RespBean respBean = RespBean.build();

    @Override
    public RespBean pageBlogs(Long current, Long limit, Boolean published, String flag,
                              Boolean share_statement, Boolean is_delete) {
        RespBean respBean = RespBean.build();
        // 创建对象
        Page<TBlog> tBlogPage = new Page<>(current,limit);
        // 构建条件
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        // 判断是否为空(构建条件)
        if (published != null){
            wrapper.eq("published",published);
        }
        if (flag != null){
            wrapper.eq("flag",flag);
        }
        if (share_statement != null){
            wrapper.eq("share_statement",share_statement);
        }
        if (is_delete != null){
            wrapper.eq("is_delete",is_delete);
        }
        // 更新时间降序排列
        wrapper.orderByDesc("update_time");
        // 调用分页方法查询
        tBlogMapper.selectPage(tBlogPage,wrapper);
        // 获取分页信息
        List<TBlog> tBlogList = tBlogPage.getRecords(); // 每页数据集合
        long size = tBlogPage.getSize(); // 每页显示的条数
        long total = tBlogPage.getTotal(); // 总记录数
        long pages = tBlogPage.getPages(); // 总页数
        // 成功报告
        respBean.setStatus(200);
        respBean.setObj(tBlogPage);
        return respBean;
    }

    @Override
    public RespBean getByTitle(String title) {
        RespBean respBean = RespBean.build();
        QueryWrapper<TBlog> queryWrapper = new QueryWrapper<TBlog>();
        queryWrapper.like("title",title);
        List<TBlog> tBlogList = tBlogMapper.selectList(queryWrapper);
        respBean.setStatus(200);
        respBean.setObj(tBlogList);
        return respBean;
    }
}
