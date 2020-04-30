FROM tomcat:jdk11-openjdk
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY target/front-2.0.war /usr/local/tomcat/webapps/front.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev", "-jar", "/usr/local/tomcat/webapps/front.war"]
