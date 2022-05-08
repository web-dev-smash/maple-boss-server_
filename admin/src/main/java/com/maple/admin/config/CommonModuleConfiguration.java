package com.maple.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.maple.common.support.Constants.COMMON_BASE_PACKAGE;
import static com.maple.common.support.Constants.INTEGRATION_BASE_PACKAGE;

@EntityScan(basePackages = COMMON_BASE_PACKAGE)
@EnableJpaRepositories(basePackages = COMMON_BASE_PACKAGE)
@ComponentScans({
        @ComponentScan(value = COMMON_BASE_PACKAGE),
        @ComponentScan(value = INTEGRATION_BASE_PACKAGE)
})
@Configuration
public class CommonModuleConfiguration {
}
