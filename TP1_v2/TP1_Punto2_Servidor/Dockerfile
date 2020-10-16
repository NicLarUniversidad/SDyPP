FROM openjdk:latest
COPY target/TP1_Punto1_Servidor-1.0-SNAPSHOT.jar /usr/src/TP1_Punto1_Servidor-1.0-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 12000
ENTRYPOINT ["java", "-jar", "/usr/src/TP1_Punto1_Servidor-1.0-SNAPSHOT.jar"]