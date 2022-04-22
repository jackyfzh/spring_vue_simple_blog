package com.example.blog.service;

import com.example.blog.entity.TType;
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
public interface TTypeService extends IService<TType> {

    RespBean pageTypes(Long current, Long size);

    RespBean getTypeByName(String name);

    RespBean deleteTypeById(Long id);

    RespBean updateType(TType tType);

    RespBean getTypeById(Long id);
}