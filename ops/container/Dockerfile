FROM tomcat:9.0.10-jre8

MAINTAINER Morgan Zhang
ARG APP_ENV=test
ENV APP_ENV=test

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./*.war /usr/local/tomcat/webapps/ROOT.war
COPY ./setenv.test.sh /usr/local/tomcat/bin/setenv.sh