package com.example.camunda_demo.controller;

import com.example.camunda_demo.service.CamundaProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("machines")
@RequiredArgsConstructor
public class MachineController {
    private final CamundaProcessService camundaProcessService;

    @PostMapping("start")
    public ResponseEntity<Boolean> start(@RequestParam(name = "key") String key) {
        camundaProcessService.start(key);
        return ResponseEntity.ok(true);
    }

    @PostMapping("approve")
    public ResponseEntity<Boolean> approve(@RequestParam(name = "key") String key) {
        camundaProcessService.approveTask(key);
        return ResponseEntity.ok(true);
    }

    @PostMapping("reject")
    public ResponseEntity<Boolean> reject(@RequestParam(name = "key") String key) {
        camundaProcessService.rejectTask(key);
        return ResponseEntity.ok(true);
    }

    @PostMapping("complete-error-task")
    public ResponseEntity<Boolean> completeErrorTask(@RequestParam(name = "key") String key) {
        camundaProcessService.completeErrorTask(key);
        return ResponseEntity.ok(true);
    }

    @PostMapping("receive-webhook-message")
    public ResponseEntity<Boolean> receiveWebhookMessage(@RequestParam(name = "key") String key) {
        camundaProcessService.receiveMessageFromWebhook(key);
        return ResponseEntity.ok(true);
    }

    @PostMapping("receive-webhook-message-with-exception")
    public ResponseEntity<Boolean> receiveWebhookMessageWithException(@RequestParam(name = "key") String key) {
        camundaProcessService.receiveMessageFromWebhookWithException(key);
        return ResponseEntity.ok(true);
    }
}
