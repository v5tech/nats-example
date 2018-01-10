package org.springframework.nats.config;

import nats.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.nats")
public class NatsProperties {

    private String[] urls;

    private boolean automaticReconnect = true;

    private long idleTimeout = Constants.DEFAULT_IDLE_TIMEOUT;

    private int maxFrameSize = Constants.DEFAULT_MAX_FRAME_SIZE;

    private long pingInterval = Constants.DEFAULT_PING_INTERVAL;

    private long reconnectWaitTime = Constants.DEFAULT_RECONNECT_TIME_WAIT;

    private boolean pedantic = false;

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    public void setAutomaticReconnect(boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getMaxFrameSize() {
        return maxFrameSize;
    }

    public void setMaxFrameSize(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    public long getPingInterval() {
        return pingInterval;
    }

    public void setPingInterval(long pingInterval) {
        this.pingInterval = pingInterval;
    }

    public long getReconnectWaitTime() {
        return reconnectWaitTime;
    }

    public void setReconnectWaitTime(long reconnectWaitTime) {
        this.reconnectWaitTime = reconnectWaitTime;
    }

    public boolean isPedantic() {
        return pedantic;
    }

    public void setPedantic(boolean pedantic) {
        this.pedantic = pedantic;
    }
}
