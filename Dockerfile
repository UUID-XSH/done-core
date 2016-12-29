FROM java:8
ADD ./build/libs/done-core-SNAPSHOT.jar done.jar
RUN sh -c 'touch /done.jar'
ENV JAVA_OPTS=""
ENV spring.datasource.username=""
ENV spring.datasource.password=""
ENV spring.datasource.url=""
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /done.jar" ]