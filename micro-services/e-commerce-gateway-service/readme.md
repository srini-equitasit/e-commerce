## backend urls
-Dprice_url=http://localhost:9082 -Dseller_url=http://localhost:9083 -Dproduct_url=http://localhost:9081
-Dzipkin_url=http://localhost:9411

## ciruit breaker
https://medium.com/bliblidotcom-techblog/spring-cloud-gateway-and-its-resilience-7ccf3d3b4f7c

## test requests
ab -n 100 http://localhost:9080/product/

ab -n 100 -c 10 http://localhost:9080/product/

## security 
https://auth0.com/docs/quickstart/backend/java-spring-security5/interactive

https://gist.github.com/abhi2495/a7dbe58a99344430389855b37b7a0523

https://auth0.com/blog/complete-guide-to-angular-user-authentication/#Calling-an-API

## get the token
curl --request POST \
--url https://dev-gh3wgihw.jp.auth0.com/oauth/token \
--header 'content-type: application/json' \
--data '{"client_id":"xoY5faRufNbYfiw8GEZgrEf78bFKoa6x","client_secret":"blLH7TuKm2Jz12hq7QoDOj44O2AqjUlg40TdmlLPgua8clwu6b-ZOFP2umdcABUg","audience":"http://e-commerce-gateway:9080","grant_type":"client_credentials"}'