FROM openjdk:13
VOLUME /tmp
EXPOSE 8081
ADD ./target/aforo255.ms.test.invoice-0.0.1-SNAPSHOT.jar app-invoices.jar
ENTRYPOINT ["java","-jar","/app-invoices.jar"]