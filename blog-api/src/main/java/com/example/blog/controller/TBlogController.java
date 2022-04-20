package com.example.blog.controller;


import com.example.blog.entity.TBlog;
import com.example.blog.form.BlogForm;
import com.example.blog.service.TBlogService;
import com.example.blog.util.RespBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/blog")
public class TBlogController {

    @Autowired
    TBlogService tBlogService;
    RespBean respBean = RespBean.build();

    /**
     * 分页查询
     * @param current
     * @param size
     * @param published
     * @param flag
     * @param share_statement
     * @param is_delete
     * @return
     */
    @GetMapping("/getByPage")
    @ApiOperation("博客分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页") ,
            @ApiImplicitParam(name = "size",value = "每页的数量"),
            @ApiImplicitParam(name = "published",value = "是否公开"),
            @ApiImplicitParam(name = "flag",value = "原创或转载"),
            @ApiImplicitParam(name = "share_statement",value = "草稿"),
            @ApiImplicitParam(name = "is_delete",value = "是否已删除"),
    })
    public RespBean getByPage(Long current, Long size, Boolean published,
                              String flag, Boolean share_statement, Boolean is_delete){
        // 返回相应分页数据
        return tBlogService.pageBlogs(current, size,published,flag,share_statement,is_delete);
    }

    @GetMapping("/getByTitle")
    @ApiOperation("通过文章标题查询")
    @ApiImplicitParam(name = "title",value = "文章的标题")
    public RespBean findByTitle(String title){
        return tBlogService.getByTitle(title);
    }

    // 保存文章
    @PostMapping("/saveBT")
    public RespBean saveBlogTag(@RequestBody HashMap<String,Object> params) {
        RespBean check = BlogForm.check(params);
        if (check.getStatus() == 500) {
            return check;
        }else {
            return tBlogService.saveBT(params);
        }
    }

    // 保存草稿
    @PostMapping("/temporarySave")
    public RespBean temporarySave(@RequestBody HashMap<String,Object> params){
        RespBean checkTemporaryBlog = BlogForm.checkTemporaryBlog(params);
        if (checkTemporaryBlog.getStatus() == 500) {
            return checkTemporaryBlog;
        }
        else {
            return tBlogService.temporarySave(params);
        }
    }

    @PutMapping("/updateBlog")
    public RespBean updateBlog(@RequestBody HashMap<String,Object> params){
        RespBean checkUpdateBlog = BlogForm.check(params);
        if (checkUpdateBlog.getStatus() == 500) {
            return checkUpdateBlog;
        }
        else {
            return tBlogService.updateBlog(params);
        }
    }

    @DeleteMapping("/logicDeleteBlog")
    public RespBean logicDeleteBlog(String id){
        TBlog tBlog = tBlogService.getById(id);
        if (tBlog != null){
            return tBlogService.logicDeleteBlog(id);
        }else {
            respBean.setStatus(500);
            respBean.setMsg("没有数据");
            return respBean;
        }
    }

    @DeleteMapping("/deleteBlog")
    public RespBean deleteBlog(String id){
        TBlog tBlog = tBlogService.getById(id);
        if (tBlog != null){
            return tBlogService.deleteBT(id);
        }else {
            respBean.setStatus(500);
            respBean.setMsg("没有数据");
            return respBean;
        }
    }

    @GetMapping("/recoveryBlog")
    public RespBean recoveryBlog(String id){
        return tBlogService.recoveryBlog(id);
    }

    @GetMapping("/getByBlogId")
    public RespBean getByBlogId(String id){
        respBean.setStatus(200);
        respBean.setMsg("查询成功");
        respBean.setObj(tBlogService.getById(id));
        return respBean;
    }
}