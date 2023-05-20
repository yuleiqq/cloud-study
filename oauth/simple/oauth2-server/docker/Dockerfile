FROM harbor.domain.com/k8s_jenkins/java8:v2
#ENV APP_FILE caller-service-1.1.0.jar
ENV  APP_HOME /usr/app
ARG  JAR_FILE
EXPOSE  8080
RUN ln -sf  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
RUN /bin/sh  -c echo  ${JAR_FILE}
COPY  ${JAR_FILE}    $APP_HOME/app.jar
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar app.jar"]
#
##启动命令
#ENTRYPOINT ["java", "-jar","-Dfile.encoding=utf-8", "app.jar","--spring.profiles.active=test"]