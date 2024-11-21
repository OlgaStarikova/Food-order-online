# Builder stage
FROM openjdk:17-jdk-alpine as builder
WORKDIR /food-service-online
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} food-order-online.jar
RUN java -Djarmode=layertools -jar food-order-online.jar extract

# Final stage
FROM openjdk:17-jdk-alpine
WORKDIR /food-service-online
COPY --from=builder food-service-online/dependencies/ ./
COPY --from=builder food-service-online/spring-boot-loader/ ./
COPY --from=builder food-service-online/snapshot-dependencies/ ./
COPY --from=builder food-service-online/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
