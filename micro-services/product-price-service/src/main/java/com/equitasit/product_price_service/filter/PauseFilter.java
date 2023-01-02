package com.equitasit.product_price_service.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@ConditionalOnProperty(name = "enablePause", havingValue = "true")
public class PauseFilter implements Filter {

    private Random random = new Random();

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        pause();
        chain.doFilter(request, response);

    }

    private void pause() {
        try {
            int randomVal = random.nextInt(4);
            log.info("waiting for seconds {} ", randomVal);
            if (randomVal > 0) {
                TimeUnit.SECONDS.sleep(randomVal);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

}
