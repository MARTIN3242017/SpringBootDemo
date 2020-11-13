package com.wk.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "MetaEventEntity", description = "元事件实体类")
@Data
public class MetaEventEntity implements Serializable {

    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "主键ID(新增时不传)", dataType = "Long")
    private Long id;
}