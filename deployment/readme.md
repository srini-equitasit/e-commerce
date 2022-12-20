## microservice design principles
https://www.developer.com/design/microservices-design-principles/


## login to docker
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 337901474843.dkr.ecr.us-east-1.amazonaws.com

## helm
https://www.eksworkshop.com/beginner/060_helm/helm_micro/create_chart/

## ingress service
https://wkrzywiec.medium.com/how-to-deploy-application-on-kubernetes-with-helm-39f545ad33b8

https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/

minikube addons enable ingress

add nginx dependecy for helm chart

run "minikube tunnel" to assign extenal ip

## enable tracing
    https://ryanharrison.co.uk/2021/08/06/distributed-tracing-spring-boot-jaeger.html
