package com.example.camunda_demo.delegate;

import com.example.camunda_demo.constants.CamundaConstants;
import com.example.camunda_demo.model.Machine;
import com.example.camunda_demo.service.MachineService;
import com.example.camunda_demo.util.DateUtils;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.cfg.TransactionState;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.runtime.Incident;

@Slf4j
@Named(CamundaConstants.INIT_MACHINE_DELEGATE)
@RequiredArgsConstructor
public class InitMachineDelegate implements JavaDelegate {
    private final MachineService machineService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("init-machine executed with details key: {}", delegateExecution.getBusinessKey());

        Machine machine = machineService.getByKey(delegateExecution.getBusinessKey());

        if (machine.isIncident()) {
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_NOW_VARIABLE, true);
            RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();
            String processInstanceId = delegateExecution.getProcessInstanceId();

            Incident incident = delegateExecution.createIncident("Failed-Init-Machine", delegateExecution.getCurrentActivityId(), "exception message details");
            //Context.getCommandContext().getTransactionContext().addTransactionListener(TransactionState.COMMITTED, commandContext -> runtimeService.suspendProcessInstanceById(processInstanceId));
            log.info("Incident created with ID: {}", incident.getId());
            delegateExecution.setVariable(CamundaConstants.INCIDENT_ID_VARIABLE, incident.getId());
            throw new RuntimeException("Unexpected error");
        } else if (machine.isStartImmediately()) {
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_NOW_VARIABLE, true);
        } else {
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_NOW_VARIABLE, false);
            delegateExecution.setVariable(CamundaConstants.START_MACHINE_AT_VARIABLE, DateUtils.nextRunDate(machine.getDelayMinutes()));
        }
    }
}
