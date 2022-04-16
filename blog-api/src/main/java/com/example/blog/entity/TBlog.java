package com.example.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="TBlog对象", description="")
public class TBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "首图地址")
    private String firstPicture;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "原创")
    private String flag;

    @ApiModelProperty(value = "是否公开")
    private Boolean published;

    @ApiModelProperty(value = "是否开启赞赏")
    private Boolean appreciation;

    @ApiModelProperty(value = "是否开启评论")
    private Boolean commentabled;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/ShangHai")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否可以回复")
    private Boolean recommend;

    @ApiModelProperty(value = "是否已发布")
    private Boolean shareStatement;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/ShangHai")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "分类id")
    private Long typeId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "评论次数")
    private Long commentCount;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDelete;


}
