FROM openjdk:8
COPY ./target/*.jar /Documents/mydocker/coindesk.jar
WORKDIR /Documents/mydocker
RUN sh -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","coindesk.jar"]