package com.example.camunda_demo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CamundaConstants {
    public static final String MACHINE_PROCESS_ID = "demo-machine-process";
    public static final String INIT_MACHINE_DELEGATE = "initMachineDelegate";
    public static final String FINAL_LOGGER_DELEGATE = "finalLoggerDelegate";
    public static final String START_MACHINE_DELEGATE = "startMachineDelegate";
    public static final String START_MACHINE_NOW_VARIABLE = "startMachineNow";
    public static final String START_MACHINE_AT_VARIABLE = "startMachineAt";
    public static final String MANAGER_APPROVAL_VARIABLE = "managerApprovalField";
    public static final String WEBHOOK_RECEIVER_VARIABLE = "receivedWebhookField";
    public static final String MANAGER_STARTED_VARIABLE = "managerStartedField";
    public static final String MANAGER_APPROVAL_FINAL_VARIABLE = "managerApprovalFinalResult";
    public static final String MANAGER_APPROVAL_TASK = "manager-approval-user-task";
    public static final String ERROR_AUDIT_USER_TASK = "error-audit-user-task";
    public static final String WEBHOOK_RECEIVER_TASK = "external-webhook-receive-task";
}
