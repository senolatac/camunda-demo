package com.example.camunda_demo.service;

import com.example.camunda_demo.constants.CamundaConstants;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaProcessService {
    private final TaskService taskService;
    private final RuntimeService runtimeService;

    public void start(String businessKey) {
        runtimeService.startProcessInstanceByKey(CamundaConstants.MACHINE_PROCESS_ID, businessKey);
    }

    public void approveTask(String businessKey) {
        completeTask(businessKey, true);
    }

    public void rejectTask(String businessKey) {
        completeTask(businessKey, false);
    }

    public void receiveMessageFromWebhook(String businessKey) {
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceBusinessKey(businessKey)
                .activityId(CamundaConstants.WEBHOOK_RECEIVER_TASK)
                .singleResult();

        Map<String, Object> variables = Map.of(CamundaConstants.WEBHOOK_RECEIVER_VARIABLE, 1000L);

        runtimeService.signal(execution.getId(), variables);
    }

    //not meaningful, you can deal with on Service-Delegate-Execution
    public void receiveMessageFromWebhookWithException(String businessKey) {
        throw new UnsupportedOperationException();
    }

    public void completeErrorTask(String businessKey) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .taskDefinitionKey(CamundaConstants.ERROR_AUDIT_USER_TASK)
                .list();

        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
    }

    private void completeTask(String businessKey, boolean approved) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .taskDefinitionKey(CamundaConstants.MANAGER_APPROVAL_TASK)
                .list();

        Map<String, Object> variables = Map.of(
                CamundaConstants.MANAGER_APPROVAL_VARIABLE, approved,
                CamundaConstants.MANAGER_APPROVAL_FINAL_VARIABLE, approved);

        for (Task task : tasks) {
            taskService.complete(task.getId(), variables);
        }
    }
}
