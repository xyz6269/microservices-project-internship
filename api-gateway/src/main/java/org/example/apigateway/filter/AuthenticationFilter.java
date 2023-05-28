package org.example.apigateway.filter;

import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.example.apigateway.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RouteValidator validator;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    log.info("test shit ain't working");
                    throw new RuntimeException("missing authorization header");
                }

                log.info("shit working lollllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                    log.info("shit working is SHIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIT");

                } catch (Exception e) {
                    log.info("invalid access..///////////////////////////////////////////.!");
                    throw new RuntimeException(e.getMessage()+ "invalid access");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}