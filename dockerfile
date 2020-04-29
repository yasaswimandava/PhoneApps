FROM openjdk:8  
EXPOSE 8080  
ADD target/UniverseSuperHeros-0.0.1-SNAPSHOT-jar-with-dependencies.jar UniverseSuperHeros-0.0.1-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","/UniverseSuperHeros-0.0.1-SNAPSHOT-jar-with-dependencies.jar"] 
