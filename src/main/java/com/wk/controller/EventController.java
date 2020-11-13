package com.wk.controller;

import com.wk.common.exception.CodeEnum;
import com.wk.common.exception.ResultVO;
import com.wk.model.MetaEventEntity;
import com.wk.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "事件")
@RestController
@RequestMapping("/meta_event")
public class EventController {
    @Resource
    private EventService eventService;

    @ApiOperation(httpMethod = "GET", value = "根据ID查")
    @GetMapping(value = "/one")
    public ResultVO getEventOne(@RequestParam Long id) {
        MetaEventEntity one = eventService.getEventOne(id);
        if (one == null)
            return ResultVO.error(CodeEnum.ID_NOT_EXIST_ERR.getCode(), "事件" + CodeEnum.ID_NOT_EXIST_ERR.getMessage());
        return ResultVO.success(one);
    }

}
