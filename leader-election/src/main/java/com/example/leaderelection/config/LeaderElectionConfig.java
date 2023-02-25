package com.example.leaderelection.config;

import com.example.leaderelection.process.BusinessProcess;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.leader.event.AbstractLeaderEvent;
import org.springframework.integration.support.SmartLifecycleRoleController;
import org.springframework.integration.zookeeper.config.LeaderInitiatorFactoryBean;

@Configuration
@Slf4j
public class LeaderElectionConfig {

    @Bean
    public CuratorFramework zookeeperClient(){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1")
                .retryPolicy(new RetryNTimes(3, 100))
                .build();

        curatorFramework.start();
        return curatorFramework;
    }

    @Bean
    public LeaderInitiatorFactoryBean leader(CuratorFramework client){
        return new LeaderInitiatorFactoryBean()
                .setClient(client)
                .setRole("fileReader")
                .setPath("/input");
    }

    @EventListener
    public void listenToLeaderEvent(AbstractLeaderEvent leaderEvent){
        log.info("leaderEvent.getRole() = " + leaderEvent.getRole());
        log.info("leaderEvent.getClass().getSimpleName() = " + leaderEvent.getClass().getSimpleName());
    }

    @Bean
    public SmartLifecycleRoleController lifecycleRoleController(BusinessProcess businessProcess){
        SmartLifecycleRoleController smartLifecycleRoleController = new SmartLifecycleRoleController();
        smartLifecycleRoleController.addLifecycleToRole("fileReader", businessProcess);
        return smartLifecycleRoleController;
    }
}
