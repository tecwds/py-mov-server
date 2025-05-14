FROM maven:3.9.6-eclipse-temurin-17 as builder
LABEL authors="tecwds"

WORKDIR /source

COPY pom.xml .
COPY pv-app/pom.xml pv-app/

# 先下好依赖
RUN mvn dependency:go-offline

COPY . .

ARG MAVEN_OPTS="-DskipTests"

# 构建 Jar 包
RUN mvn clean package $MAVEN_OPTS

# 解压 Jar 包
RUN mkdir -p pv-app/target/dependency && (cd pv-app/target/dependency; jar -xf ../*.jar)

FROM openjdk:17-alpine as runner

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

WORKDIR /app

ARG DEPENDENCY=/source/pv-app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app/classes
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF

ENTRYPOINT ["java", "-cp", "classes:lib/*", "top.wpaint.pymov.PyMovApplication"]