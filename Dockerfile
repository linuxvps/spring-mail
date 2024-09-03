# Use the official OpenJDK 22 image as the base
FROM openjdk:22-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the .jar file into the container
COPY target/mail-0.0.1-SNAPSHOT.jar /app/

# Expose the port that the application will use
EXPOSE 8080

# Run the command to start the application when the container launches
CMD ["java", "-jar", "mail-0.0.1-SNAPSHOT.jar"]