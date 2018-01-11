package org.springframework.nats.config;

import nats.client.Nats;
import nats.client.NatsConnector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(NatsProperties.class)
@ConditionalOnClass(Nats.class)
public class NatsAutoConfiguration {

    private final static String DEFAULT_NATS_HOST = "nats://127.0.0.1:4222";

    @Bean
    @ConditionalOnMissingBean(Nats.class)
    public Nats nats(NatsProperties properties) {
        NatsConnector natsConnector = new NatsConnector();
        if (properties.getUrls().length > 0) {
            for (String url : properties.getUrls()) {
                natsConnector.addHost(url);
            }
        } else {
            natsConnector.addHost(DEFAULT_NATS_HOST);
        }
        natsConnector.pedantic(properties.isPedantic());
        natsConnector.automaticReconnect(properties.isAutomaticReconnect());
        natsConnector.reconnectWaitTime(properties.getReconnectWaitTime(), TimeUnit.MILLISECONDS);
        natsConnector.idleTimeout(properties.getIdleTimeout());
        natsConnector.maxFrameSize(properties.getMaxFrameSize());
        natsConnector.pingInterval(properties.getPingInterval());
        return natsConnector.connect();
    }

}
