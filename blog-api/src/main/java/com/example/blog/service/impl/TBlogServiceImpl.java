package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.TBlog;
import com.example.blog.entity.TBlogTags;
import com.example.blog.entity.TTag;
import com.example.blog.mapper.TBlogMapper;
import com.example.blog.mapper.TBlogTagsMapper;
import com.example.blog.mapper.TTagMapper;
import com.example.blog.service.TBlogService;
import com.example.blog.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@Transactional(rollbackFor=Exception.class)  // 开启事务
@Service
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements TBlogService {

    @Autowired
    TBlogMapper tBlogMapper;
    @Autowired
    TTagMapper tTagMapper;
    @Autowired
    TBlogTagsMapper tBlogTagsMapper;

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

    @Override
    public RespBean saveBT(HashMap<String, Object> params) {
        // 保存博客
        RespBean respBean = RespBean.build();
        TBlog tBlog = new TBlog();
        tBlog.setTitle((String) params.get("title"));
        tBlog.setDescription((String) params.get("description"));
        tBlog.setFirstPicture((String) params.get("first_picture"));
        tBlog.setContent((String) params.get("content"));
        tBlog.setTypeId(Long.parseLong(params.get("type_id").toString()));
        tBlog.setFlag((String) params.get("flag"));
        tBlog.setPublished(Boolean.valueOf(params.get("published").toString()));
        tBlog.setShareStatement(true); // 设置状态为已发布
        // 保存文章
        int result_blog = tBlogMapper.insert(tBlog);
        int result_tag = 0;
        int resule_bt = 0;
        List<String> tags = (List) params.get("tags");
        for (String tag: tags) {
            TTag tTag = new TTag();
            tTag.setName(tag);
            // 保存关键词
            result_tag = tTagMapper.insert(tTag);
            TBlogTags tBlogTags = new TBlogTags();
            tBlogTags.setBlogsId(tBlog.getId());
            tBlogTags.setTagsId(tTag.getId());
            resule_bt = tBlogTagsMapper.insert(tBlogTags);
        }
        if (result_blog != 0 && result_tag != 0 && resule_bt !=0){
            respBean.setStatus(200);
            respBean.setMsg("添加成功！");
            return respBean;
        }
        respBean.setMsg("添加失败");
        return respBean;
    }

    @Override
    public RespBean temporarySave(HashMap<String, Object> params) {
        // 暂存博客草稿
        RespBean respBean = RespBean.build();
        TBlog tBlog = new TBlog();
        tBlog.setTitle((String) params.get("title"));
        tBlog.setDescription((String) params.get("description"));
        tBlog.setFirstPicture((String) params.get("first_picture"));
        tBlog.setContent((String) params.get("content"));
        // 保存文章
        int result_blog = tBlogMapper.insert(tBlog);
        if (result_blog != 0){
            respBean.setStatus(200);
            respBean.setMsg("添加成功！");
            return respBean;
        }
        respBean.setMsg("添加失败");
        return respBean;
    }

    @Override
    public RespBean updateBlog(HashMap<String, Object> params) {
        RespBean respBean = RespBean.build();
        // 根据id查询文章
        TBlog tBlog = tBlogMapper.selectById(Long.parseLong(params.get("id").toString()));
        tBlog.setTitle((String) params.get("title"));
        tBlog.setDescription((String) params.get("description"));
        tBlog.setFirstPicture((String) params.get("first_picture"));
        tBlog.setContent((String) params.get("content"));
        tBlog.setTypeId(Long.parseLong(params.get("type_id").toString()));
        tBlog.setFlag((String) params.get("flag"));
        tBlog.setPublished(Boolean.valueOf(params.get("published").toString()));
        tBlog.setUpdateTime(LocalDateTime.now());
        // 更新文章
        int result_blog = tBlogMapper.updateById(tBlog);
        int result_tag = 0;
        int resule_bt = 0;
        // 删除标签与记录
        QueryWrapper<TBlogTags> queryWrapper1 = new QueryWrapper<TBlogTags>();
        queryWrapper1.eq("blogs_id",tBlog.getId());
        List<TBlogTags> blogTagsList =  tBlogTagsMapper.selectList(queryWrapper1);
        for (TBlogTags bt : blogTagsList) {
            // 删除标签表的数据
            tTagMapper.deleteById(bt.getTagsId());
            // 删除博客标签关联表的数据
            QueryWrapper<TBlogTags> queryWrapper = new QueryWrapper<TBlogTags>();
            queryWrapper.eq("tags_id",bt.getTagsId());
            // 删除博客标签关联表的数据
            tBlogTagsMapper.delete(queryWrapper);
        }
        // 重新添加标签
        List<String> tags = (List) params.get("tags");
        for (String tag : tags) {
            // 保存标签
            TTag tTag = new TTag();
            tTag.setName(tag);
            result_tag = tTagMapper.insert(tTag);
            // 保存中间表记录
            TBlogTags tBlogTags = new TBlogTags();
            tBlogTags.setBlogsId(tBlog.getId());
            tBlogTags.setTagsId(tTag.getId());
            resule_bt = tBlogTagsMapper.insert(tBlogTags);
        }
        if (result_blog != 0 && result_tag != 0 && resule_bt !=0){
            respBean.setStatus(200);
            respBean.setMsg("更新博客成功！");
            return respBean;
        }
        respBean.setMsg("更新博客失败");
        return respBean;
    }

    @Override
    public RespBean logicDeleteBlog(String id) {
        RespBean respBean = RespBean.build();
        TBlog tBlog = tBlogMapper.selectById(id);
        if (tBlog != null){
            tBlog.setIsDelete(true);
            tBlog.setUpdateTime(LocalDateTime.now());
            tBlogMapper.updateById(tBlog);
            respBean.setStatus(200);
            respBean.setMsg("删除博客成功！");
            return respBean;
        }else {
            respBean.setStatus(500);
            respBean.setMsg("删除博客失败");
            return respBean;
        }
    }

    @Override
    public RespBean deleteBT(String id) {
        RespBean respBean = RespBean.build();
        TBlog tBlog = tBlogMapper.selectById(id);
        // 删除标签和中间表记录
        QueryWrapper<TBlogTags> queryWrapper1 = new QueryWrapper<TBlogTags>();
        queryWrapper1.eq("blogs_id",tBlog.getId());
        List<TBlogTags> blogTagsList =  tBlogTagsMapper.selectList(queryWrapper1);
        int r_tag = 0;
        int r_blog_tag = 0;
        for (TBlogTags bt : blogTagsList) {
            // 删除标签表的数据
            r_tag = tTagMapper.deleteById(bt.getTagsId());
            //删除博客标签关联表的数据
            QueryWrapper<TBlogTags> queryWrapper = new QueryWrapper<TBlogTags>();
            queryWrapper.eq("tags_id",bt.getTagsId());
            // 删除博客标签关联表的数据
            r_blog_tag = tBlogTagsMapper.delete(queryWrapper);
        }
        // 删除博客文章
        int r_blog = tBlogMapper.deleteById(id);
        if (r_tag != 0 && r_blog_tag!= 0 && r_blog!= 0){
            respBean.setStatus(200);
            respBean.setMsg("删除博客和标签成功");
            return respBean;
        }else {
            respBean.setStatus(500);
            respBean.setMsg("删除博客和标签失败");
            return respBean;
        }

    }

    @Override
    public RespBean recoveryBlog(String id) {
        RespBean respBean = RespBean.build();
        TBlog tBlog = tBlogMapper.selectById(id);
        if (tBlog != null){
            tBlog.setIsDelete(false);
            tBlog.setUpdateTime(LocalDateTime.now());
            tBlogMapper.updateById(tBlog);
            respBean.setStatus(200);
            respBean.setMsg("还原博客成功！");
            return respBean;
        }else {
            respBean.setStatus(500);
            respBean.setMsg("还原博客失败");
            return respBean;
        }
    }

    @Override
    public RespBean getByBlogId(String id) {
        RespBean respBean = RespBean.build();
        respBean.setStatus(200);
        respBean.setObj(tBlogMapper.getByBlogId(id));
        return respBean;
    }
}
