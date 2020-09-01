FROM openjdk:13
VOLUME /tmp
EXPOSE 8082
ADD ./target/aforo255.ms.test.transaction-0.0.1-SNAPSHOT.jar app-transaction.jar
ENTRYPOINT ["java","-jar","/app-transaction.jar"]
