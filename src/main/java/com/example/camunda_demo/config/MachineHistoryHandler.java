package com.example.camunda_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;

import java.util.List;

@Slf4j
public class MachineHistoryHandler implements HistoryEventHandler {

    @Override
    public void handleEvent(HistoryEvent historyEvent) {
        log.warn("Event received type: {} id: {}", historyEvent.getEventType(), historyEvent.getId());
    }

    @Override
    public void handleEvents(List<HistoryEvent> list) {

    }
}
