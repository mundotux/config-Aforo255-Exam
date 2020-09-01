FROM openjdk:13
VOLUME /tmp
EXPOSE 8006
ADD ./target/aforo255.ms.test.pay-0.0.1-SNAPSHOT.jar app-pay.jar
ENTRYPOINT ["java","-jar","/app-pay.jar"]