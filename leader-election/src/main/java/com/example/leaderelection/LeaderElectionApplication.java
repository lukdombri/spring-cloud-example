package com.example.leaderelection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeaderElectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaderElectionApplication.class, args);
        try {
            Thread.sleep(10_000_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
