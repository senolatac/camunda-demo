package com.example.camunda_demo.service;

import com.example.camunda_demo.model.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    public static final List<Machine> MACHINES = List.of(
            Machine.builder()
                    .key("machine-business-key-1")
                    .startImmediately(true)
                    .incident(false)
                    .build(),
            Machine.builder()
                    .key("machine-business-key-2")
                    .startImmediately(false)
                    .incident(false)
                    .delayMinutes(2L)
                    .build(),
            Machine.builder()
                    .key("machine-business-key-3")
                    .startImmediately(true)
                    .incident(true)
                    .build()
    );

    public Machine getByKey(String key) {
        return MACHINES.stream()
                .filter(machine -> machine.getKey().equals(key))
                .findFirst()
                .orElseThrow();
    }
}
