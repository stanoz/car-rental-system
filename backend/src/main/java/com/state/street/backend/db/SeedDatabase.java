package com.state.street.backend.db;

import com.state.street.backend.BackendApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SeedDatabase {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(BackendApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        context.getBean(DatabaseSeeder.class).seed();

        context.close();
    }
}
