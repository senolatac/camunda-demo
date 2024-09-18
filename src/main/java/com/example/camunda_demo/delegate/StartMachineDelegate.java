package com.example.camunda_demo.delegate;

import com.example.camunda_demo.constants.CamundaConstants;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
@Named(CamundaConstants.START_MACHINE_DELEGATE)
public class StartMachineDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("start-machine executed with details key: {}", delegateExecution.getBusinessKey());

        delegateExecution.setVariable(CamundaConstants.MANAGER_STARTED_VARIABLE, true);
    }
}
