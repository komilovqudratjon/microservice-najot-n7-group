package uz.najot.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO
 * @date: 07 April 2024 $
 * @time: 6:14 PM 29 $
 * @author: Qudratjon Komilov
 */
@Configuration
public class ConfigApp {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
