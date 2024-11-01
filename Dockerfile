# Builder stage
FROM openjdk:17-jdk-alpine as builder
WORKDIR /food-order-online
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} food-order-online.jar
RUN java -Djarmode=layertools -jar food-order-online.jar extract

# Final stage
FROM openjdk:17-jdk-alpine
WORKDIR /food-order-online
COPY --from=builder food-order-online/dependencies/ ./
COPY --from=builder food-order-online/spring-boot-loader/ ./
COPY --from=builder food-order-online/snapshot-dependencies/ ./
COPY --from=builder food-order-online/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
