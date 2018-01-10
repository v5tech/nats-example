package net.ameizi;

import nats.client.Message;
import nats.client.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class App {

    @Autowired
    private nats.client.Nats nats;

    /**
     * 发布消息
     */
    @Order(2)
    @Component
    class Publish implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            scheduled();
        }

        @Scheduled(fixedRate = 5_000L)
        public void scheduled() {
            nats.publish("some.nats.subject", "message content");
        }

    }

    /**
     * 订阅消息
     */
    @Order(1)
    @Component
    class Subscribe implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            nats.subscribe("some.nats.subject", new MessageHandler() {
                @Override
                public void onMessage(Message message) {
                    System.out.println("Received: " + message);
                }
            });
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
