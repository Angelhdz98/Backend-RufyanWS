package com.example.PaginaWebRufyan.ControllerTests;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class DockerSmokeTest {

    @Test
    void dockerWorks() {
        try (GenericContainer<?> container = new GenericContainer<>("alpine:latest")
                .withCommand("sleep", "5")) {
            container.start();
            assertTrue(container.isRunning());
        }
    }
}