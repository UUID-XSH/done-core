FROM java:8
ADD ./build/libs/done-core-SNAPSHOT.jar done.jar
RUN sh -c 'touch /done.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /done.jar" ]