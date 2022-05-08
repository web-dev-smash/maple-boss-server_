package com.maple.integration.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "slack")
    public SlackProperties slackProperties() {
        return new SlackProperties();
    }

    @Bean
    public Slack slack() {
        return Slack.getInstance();
    }

    @Bean
    public MethodsClient slackClient(Slack slack, SlackProperties slackProperties) {
        return slack.methods(slackProperties.getBotToken());
    }
}
