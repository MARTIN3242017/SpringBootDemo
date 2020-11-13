package com.wk.service.impl;

import com.wk.mapper.EventMapper;
import com.wk.model.MetaEventEntity;
import com.wk.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public MetaEventEntity getEventOne(Long id) {
        return null;
    }

}
