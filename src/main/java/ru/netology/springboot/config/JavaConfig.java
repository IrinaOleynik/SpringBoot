package ru.netology.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboot.profile.DevProfile;
import ru.netology.springboot.profile.ProductionProfile;
import ru.netology.springboot.profile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(
            prefix = "netology",
            name = "profile.dev",
            havingValue = "true")

    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "netology",
            name = "profile.dev",
            havingValue = "false",
            matchIfMissing = true)

    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}

