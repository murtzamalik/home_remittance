# Multi-stage Spring Boot WAR builder/runner.
# Callers: docker-compose.yml services login/fileupload/fi/um/transactions
# User instruction: deploy full stack on local docker
FROM maven:3.9.9-eclipse-temurin-11 AS build
ARG SERVICE_DIR
WORKDIR /src
COPY ${SERVICE_DIR}/pom.xml ./pom.xml
COPY ${SERVICE_DIR}/src ./src
RUN sed -i 's#<scope>provided</scope>##g' pom.xml || true
RUN mvn -q -DskipTests package

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /src/target/*.war /app/app.war
EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx768m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.war --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-docker}"]
