package com.example.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.blog.entity.TType;
import com.example.blog.service.TTypeService;
import com.example.blog.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/type")
public class TTypeController {

    @Autowired
    TTypeService tTypeService;

    RespBean respBean = RespBean.build();

    @PostMapping("/saveType")
    public RespBean saveType(@RequestBody TType tType){
        if (StringUtils.isEmpty(tType.getName())){
            respBean.setStatus(500);
            respBean.setMsg("分类专栏名称不能为空！");
            return respBean;
        }
        QueryWrapper<TType> queryWrapper = new QueryWrapper<TType>();
        queryWrapper.eq("name",tType.getName());
        List<TType> tBlogList = tTypeService.list(queryWrapper);
        if (tBlogList.size() > 0){
            respBean.setStatus(500);
            respBean.setMsg("添加失败，已有该分类专栏名称");
            return respBean;
        }
        Boolean result = tTypeService.save(tType);
        if (result){
            respBean.setStatus(200);
            respBean.setMsg("添加成功！");
            return respBean;
        }else {
            respBean.setStatus(500);
            respBean.setMsg("添加失败！");
            return respBean;
        }
    }

    @GetMapping("/getAllType")
    public RespBean getAllType(){
        List<TType> typeList = tTypeService.list();
        return RespBean.ok("查询成功！",typeList);
    }

}