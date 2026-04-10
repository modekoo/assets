FROM gradle:8.7-jdk21 AS builder
WORKDIR /build

COPY build.gradle settings.gradle ./
COPY gradle ./gradle
# OCI cp ~/.gradle/gradle.properties ./gradle.properties (proxy)
COPY gradle.properties ./
COPY src ./src

RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

# jar 이름을 assets.jar로 고정
COPY --from=builder /build/build/libs/*.jar /app/assets.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/assets.jar"]
