FROM maven:3.8.3-openjdk-17 as base
WORKDIR /app
#COPY .mvn/ .mvn
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean test