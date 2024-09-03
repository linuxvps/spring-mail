# Use the official OpenJDK 22 image as the base
FROM openjdk:22

# Set the working directory to /app
WORKDIR /application

# Copy the .jar file into the container
COPY . .

# Expose the port that the application will use
EXPOSE 8888

# Run the command to start the application when the container launches
CMD ["java", "-jar", "target/mail-0.0.1-SNAPSHOT.jar"]