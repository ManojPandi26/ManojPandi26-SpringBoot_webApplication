FROM maven:3.8.5-openjdk AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/TLCRoomBooking-0.0.1-SNAPSHOT.jar TLCRoomBooking.jar
EXPOSE 8080
ENTRYPOINT  ["java","-jar","TLCRoomBooking.jar"]