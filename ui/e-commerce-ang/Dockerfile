## stage 1
FROM node:14-alpine as build-step
RUN mkdir -p /app

WORKDIR /app
COPY . /app

#RUN pwd
#RUN ls -l

#RUN npm install --loglevel verbose
RUN npm install

RUN npm run build --prod


## stage 2
FROM nginx:1.22.1-alpine
COPY --from=build-step /app/dist/e-commerce-ang /usr/share/nginx/html

COPY default.conf.template /etc/nginx/templates/default.conf.template

CMD ["envsubst" " < " "assets/env.template.config.js" " > " "assets/env.config.js"]

EXPOSE 80

#docker run -p4200:80 -e helloApiUrl=http://ec2-100-26-3-123.compute-1.amazonaws.com:9083/hello 337901474843.dkr.ecr.us-east-1.amazonaws.com/equitas-it:hello-angular-ui
