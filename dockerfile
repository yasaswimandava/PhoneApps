FROM openjdk:8  
EXPOSE 8080  
ADD target/PhoneApps-0.0.1-SNAPSHOT.jar PhoneApps-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/PhoneApps-0.0.1-SNAPSHOT.jar"] 
