package com.example.camunda_demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Machine {
    private String key;

    private boolean startImmediately;

    private Long delayMinutes;
}
