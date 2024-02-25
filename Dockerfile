# Use an official Ubuntu runtime as a parent image
FROM ubuntu

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk

WORKDIR /app
COPY BankApp.jar /app/

RUN chmod a+x BankApp.jar

CMD ["java", "-Djava.awt.headless=false", "-jar", "BankApp.jar"]
