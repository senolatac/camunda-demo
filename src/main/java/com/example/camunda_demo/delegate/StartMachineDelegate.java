package com.example.camunda_demo.delegate;

import com.example.camunda_demo.constants.CamundaConstants;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Incident;

@Slf4j
@Named(CamundaConstants.START_MACHINE_DELEGATE)
public class StartMachineDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("start-machine executed with details key: {}", delegateExecution.getBusinessKey());

        delegateExecution.setVariable(CamundaConstants.MANAGER_STARTED_VARIABLE, true);

        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();

        if (delegateExecution.hasVariable(CamundaConstants.INCIDENT_ID_VARIABLE)) {
            Incident prevIncident = runtimeService.createIncidentQuery()
                    .incidentId((String) delegateExecution.getVariable(CamundaConstants.INCIDENT_ID_VARIABLE))
                    .singleResult();

            log.info("Prev-incident detected: {}", prevIncident);

            //Incident incident = delegateExecution.createIncident("Failed Job in StartMachine", "", "exception message details");
            //log.info("Current-incident created: {}", incident.getId());
        }
    }
}
