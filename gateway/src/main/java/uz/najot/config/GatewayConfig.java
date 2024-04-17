package uz.najot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * @description: TODO
 * @date: 17 April 2024 $
 * @time: 6:03 PM 08 $
 * @author: Qudratjon Komilov
 */
@Configuration
public class GatewayConfig {

    @Bean
    public AntPathMatcher pathMatcher() {
        return new AntPathMatcher();
    }

}
