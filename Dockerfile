# Use an official Java runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the necessary files (including your compiled Java classes and test resources) to the container
COPY target/classes/ /usr/src/app/classes/
COPY target/test-classes/ /usr/src/app/test-classes/
COPY src/test/resources/ /usr/src/app/resources/

# Copy your TestNG XML file (if needed)
COPY testng.xml /usr/src/app/

# Define environment variables (if needed)
ENV ENVIRONMENT=production

# Run your Selenium tests using TestNG
CMD ["java", "-cp", "/usr/src/app/classes:/usr/src/app/test-classes", "org.testng.TestNG", "testng.xml"]
