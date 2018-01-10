package net.ameizi;

import nats.client.Nats;
import nats.client.NatsConnector;

import java.util.Timer;
import java.util.TimerTask;

public class NatsPubApp {

    public static void main(String[] args) {
        Nats nats = new NatsConnector()
                .addHost("nats://10.61.8.7:4222")
                .addHost("nats://10.61.8.10:4222")
                .addHost("nats://10.61.8.18:4222")
                .automaticReconnect(true)
                .pedantic(true)
                .connect();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                nats.publish("foo", "Hello world!");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,1000,2000);
    }

}
