package com.example.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.blog.entity.TTag;
import com.example.blog.service.TTagService;
import com.example.blog.util.RespBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/tag")
public class TTagController {

    @Autowired
    TTagService tTagService;

    RespBean respBean = RespBean.build();

    @PostMapping("/saveTag")
    public RespBean saveTag(@RequestBody TTag tTag){
        if (StringUtils.isEmpty(tTag.getName())){
            respBean.setStatus(500);
            respBean.setMsg("标签名称不能为空！");
            return respBean;
        }
        QueryWrapper<TTag> queryWrapper = new QueryWrapper<TTag>();
        queryWrapper.eq("name",tTag.getName());
        List<TTag> tagList = tTagService.list(queryWrapper);
        if (tagList.size() > 0){
            respBean.setStatus(500);
            respBean.setMsg("添加失败，已有该标签名称");
            return respBean;
        }
        Boolean result = tTagService.save(tTag);
        if (result){
            respBean.setStatus(200);
            respBean.setMsg("添加标签成功！");
            return respBean;
        }else {
            respBean.setStatus(500);
            respBean.setMsg("添加标签失败！");
            return respBean;
        }

    }

    @GetMapping("/getAllTag")
    public RespBean getAllTag(){
        List<TTag> tagList = tTagService.list();
        return RespBean.ok("查询成功！",tagList);
    }

    @GetMapping("/getTagByPage")
    @ApiOperation("标签的分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页") ,
            @ApiImplicitParam(name = "size",value = "每页的数量"),
    })
    public RespBean getTagByPage(Long current, Long size){
        return tTagService.pageTags(current, size);
    }

    @GetMapping("/getTagByName")
    public RespBean getTagByName(String name){
        return tTagService.getTagByName(name);
    }

    @PutMapping("/updateTag")
    public RespBean updateTag(@RequestBody TTag tTag){
        if (StringUtils.isEmpty(tTag.getName())){
            respBean.setMsg("分类名称不能为空");
            return respBean;
        }
        return tTagService.updateTag(tTag);
    }

    @DeleteMapping("deleteTagById")
    public RespBean deleteTagById(Long id){
        return tTagService.deleteTagById(id);
    }

    @GetMapping("/getTagById")
    public RespBean getTagById(Long id){
        return tTagService.getTagById(id);
    }

}