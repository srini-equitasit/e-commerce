package com.equitasit.gateway_service.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(1)
@Slf4j
public class ECommerceTraceFilter implements WebFilter {

    @Autowired
    private Tracer tracer;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {

        if (tracer != null && tracer.currentSpan() != null) {
            serverWebExchange.getResponse()
                    .getHeaders().add("X-Correlation-ID", tracer.currentSpan().context().traceId());
        } else {
            log.info("tracer {}", tracer.currentSpan());
        }

        return webFilterChain.filter(serverWebExchange);
    }

}
