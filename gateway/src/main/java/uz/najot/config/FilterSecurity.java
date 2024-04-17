package uz.najot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import uz.najot.order.AuthApi;

import java.util.List;
import java.util.Set;

/**
 * @description: TODO
 * @date: 17 April 2024 $
 * @time: 5:40 PM 20 $
 * @author: Qudratjon Komilov
 */
@Slf4j
@Service
public class FilterSecurity implements GlobalFilter {

    private Set<String> allowedEndPoint = Set.of(
            "/auth/login",
            "/auth/register"
    );

    private final AntPathMatcher pathMatcher;

    private final AuthApi authApi;

    public FilterSecurity(AntPathMatcher pathMatcher,@Lazy AuthApi authApi) {
        this.pathMatcher = pathMatcher;
        this.authApi = authApi;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders headers = exchange.getRequest().getHeaders();

        RequestPath path = exchange.getRequest().getPath();

        log.info("Path: {}", path);

        if (allowedEndPoint.stream().anyMatch(p -> p.equals(path.toString()))) {

            return chain.filter(exchange);

        }

        log.info("Request: {}", headers);

        List<String> strings = headers.get("eshmat");

        if (strings == null || strings.isEmpty()) {

            log.error("Header not found");

            throw new RuntimeException("Header not found");

        }

        return chain.filter(exchange);
    }

}
