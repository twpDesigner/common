//package com.twp.common.config;
//
//import com.hazelcast.config.Config;
//import com.hazelcast.core.Hazelcast;
//import com.hazelcast.core.HazelcastInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class HazelCastConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public HazelcastInstance getInstance() {
//        String applicationName = env.getProperty("spring.application.name");
//        Config config = new Config();
//        config.getGroupConfig().setName(applicationName);
//        config.setInstanceName(applicationName);
//        return Hazelcast.getOrCreateHazelcastInstance(config);
//    }
//
//}