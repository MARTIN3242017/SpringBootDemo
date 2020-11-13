package com.wk.mapper;

import com.wk.model.MetaEventEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author WANKAI
 * @Date 2020/9/21 2:50 下午
 */
@Mapper
@Repository
public interface EventMapper {

    MetaEventEntity getEventOne(@Param("id") Long id);

}
