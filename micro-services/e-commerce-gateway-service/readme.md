## backend urls
-Dprice_url=http://localhost:9082 -Dseller_url=http://localhost:9083 -Dproduct_url=http://localhost:9081
-Dzipkin_url=http://localhost:9411

## ciruit breaker
https://medium.com/bliblidotcom-techblog/spring-cloud-gateway-and-its-resilience-7ccf3d3b4f7c

## test requests
ab -n 100 http://localhost:9080/product/

ab -n 100 -c 10 http://localhost:9080/product/