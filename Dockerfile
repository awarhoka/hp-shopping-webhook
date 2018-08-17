FROM java:8
VOLUME /tmp
ADD hp-shopping-webhook-m1-1.0-SNAPSHOT.jar
RUN bash -c 'touch /hp-shopping-webhook-m1-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/hp-shopping-webhook-m1-1.0-SNAPSHOT.jar"]