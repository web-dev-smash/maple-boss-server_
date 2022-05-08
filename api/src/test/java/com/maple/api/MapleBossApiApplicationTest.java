package com.maple.api;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MapleBossApiApplicationTest {

    @Test
    void contextLoads() throws SlackApiException, IOException {
        final Slack slack = Slack.getInstance();

        final MethodsClient client = slack.methods("xoxb-3365127034325-3517887020528-HUEpG4p0WDHtuq2eTSUMivSD");

        client.chatPostMessage(builder -> {
            return builder
                    .username("Maple Boss API")
                    .channel("#maple-boss")
                    .text("슬랙 메시지 테스트");
        });
    }
}