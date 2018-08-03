From tomcat:8-jre8

ARG version

COPY ./target/morgan-$version.war /usr/local/tomcat/webapps/myapp.war
