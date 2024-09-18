package com.example.camunda_demo.delegate;

import com.example.camunda_demo.constants.CamundaConstants;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

@Slf4j
@Named(CamundaConstants.FINAL_LOGGER_DELEGATE)
@RequiredArgsConstructor
public class FinalLoggerDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("final-logger executed with details key: {}", delegateExecution.getBusinessKey());

        for (Map.Entry<String, Object> entry : delegateExecution.getVariables().entrySet()) {
            log.info("final variable key: {} value: {}", entry.getKey(), entry.getValue());
        }
    }
}
