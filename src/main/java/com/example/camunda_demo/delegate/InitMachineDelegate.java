package com.example.camunda_demo.delegate;

import com.example.camunda_demo.constants.CamundaConstants;
import com.example.camunda_demo.model.Machine;
import com.example.camunda_demo.service.MachineService;
import com.example.camunda_demo.util.DateUtils;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
@Named(CamundaConstants.INIT_MACHINE_DELEGATE)
@RequiredArgsConstructor
public class InitMachineDelegate implements JavaDelegate {
    private final MachineService machineService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("init-machine executed with details key: {}", delegateExecution.getBusinessKey());

        Machine machine = machineService.getByKey(delegateExecution.getBusinessKey());

        if (machine.isStartImmediately()) {
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_NOW_VARIABLE, true);
        } else {
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_NOW_VARIABLE, false);
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_AT_VARIABLE, DateUtils.nextRunDate(machine.getDelayMinutes()));
        }
    }
}
