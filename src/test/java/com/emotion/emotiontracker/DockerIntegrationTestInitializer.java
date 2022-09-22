package com.emotion.emotiontracker;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

public class DockerIntegrationTestInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:9.4");

    static {
        postgreDBContainer.start();
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreDBContainer.getUsername(),
                "spring.datasource.password=" + postgreDBContainer.getPassword()
        );

    }
}
