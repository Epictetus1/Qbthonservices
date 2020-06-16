FROM java:8
EXPOSE 9000
ADD /target/*.jar loginservice.jar
ENTRYPOINT [“java”,”-jar”,”loginservice.jar”]