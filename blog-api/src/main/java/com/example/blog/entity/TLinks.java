package com.example.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jackyfeng
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TLinks对象", description="")
public class TLinks implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "博客地址")
    private String blogAddress;

    @ApiModelProperty(value = "博客名称")
    private String blogName;

    @ApiModelProperty(value = "首图")
    private String pictureAddress;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
