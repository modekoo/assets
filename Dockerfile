FROM gradle:8.7-jdk21 AS builder
WORKDIR /build

ENV HTTP_PROXY=${HTTP_PROXY}
ENV HTTPS_PROXY=${HTTPS_PROXY}
ENV NO_PROXY=${NO_PROXY}
ENV http_proxy=${HTTP_PROXY}
ENV https_proxy=${HTTPS_PROXY}
ENV no_proxy=${NO_PROXY}

COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradle.properties ./   #OCI cp ~/.gradle/gradle.properties ./gradle.properties (proxy)
COPY src ./src

RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

# jar 이름을 assets.jar로 고정
COPY --from=builder /build/build/libs/*.jar /app/assets.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/assets.jar"]
