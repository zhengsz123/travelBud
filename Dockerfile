From tomcat:8-jre8

ARG version
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./target/morgan-$version.war /usr/local/tomcat/webapps/ROOT.war

ARG APP_ENV=prod
ENV APP_ENV ${APP_ENV}
EXPOSE 8080

COPY ./setenv.${APP_ENV}.sh  /usr/local/tomcat/bin/setenv.sh
