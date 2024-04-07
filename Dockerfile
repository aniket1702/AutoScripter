# Use a base image that includes Java
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the compiled Java classes to the container
COPY target/Classes/ /usr/src/app/classes/

# Optionally, you can copy other resources if needed
COPY src/test/resources/ /usr/src/app/resources/

# Specify the entry point or command to run when the container starts
# For example, to run a Java application, you would use:
# CMD ["java", "-cp", "/usr/src/app/classes", "com.autoscripter.MainClass"]
