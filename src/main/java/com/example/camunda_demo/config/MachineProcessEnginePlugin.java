package com.example.camunda_demo.config;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordering.DEFAULT_ORDER - 1)
public class MachineProcessEnginePlugin implements ProcessEnginePlugin {
    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        MachineHistoryHandler machineHistoryHandler = new MachineHistoryHandler();

        processEngineConfiguration.setHistoryEventHandler(machineHistoryHandler);
    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }
}
