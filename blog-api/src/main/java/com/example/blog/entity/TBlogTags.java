package com.example.blog.entity;

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
@ApiModel(value="TBlogTags对象", description="")
public class TBlogTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客id")
    private Long blogsId;

    @ApiModelProperty(value = "标签id")
    private Long tagsId;


}
