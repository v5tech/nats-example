package net.ameizi;

import nats.client.Message;
import nats.client.MessageHandler;
import nats.client.Nats;
import nats.client.NatsConnector;

public class NatsSubApp {

    public static void main(String[] args) {
        Nats nats = new NatsConnector()
                .addHost("nats://10.61.8.7:4222")
                .addHost("nats://10.61.8.10:4222")
                .addHost("nats://10.61.8.18:4222")
                .automaticReconnect(true)
                .pedantic(true)
                .connect();

        nats.subscribe("foo", new MessageHandler() {
            @Override
            public void onMessage(Message message) {
                System.out.println("Received: " + message);
            }
        });

    }

}
