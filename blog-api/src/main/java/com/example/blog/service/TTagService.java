package com.example.blog.service;

import com.example.blog.entity.TTag;
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
public interface TTagService extends IService<TTag> {

    RespBean pageTags(Long current, Long size);

    RespBean getTagByName(String name);

    RespBean updateTag(TTag tTag);

    RespBean deleteTagById(Long id);

    RespBean getTagById(Long id);
}
