FROM openjdk:17
MAINTAINER Ashish Hole "your@email.com"

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar to the container
COPY target/payments-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]