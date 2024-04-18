# Use a specific version of Maven
FROM maven:3.8.5-openjdk AS build

# Set the working directory in the container
WORKDIR /app

# Copy the entire project to the working directory
COPY . .

# Build the project with Maven
RUN mvn clean package -DskipTests

# Use a slim OpenJDK image as the base image
FROM openjdk:17.0.1-jdk-slim AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the build stage to the runtime stage
COPY --from=build /target/TLCRoomBooking-0.0.1-SNAPSHOT.jar .

# Expose port 8080 to the outside world
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "TLCRoomBooking-0.0.1-SNAPSHOT.jar"]
