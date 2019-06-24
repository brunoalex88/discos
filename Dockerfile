FROM maven:3.6.1-jdk-8
CMD ["mkdir", "-p", "/usr/app"]
COPY . /usr/app
WORKDIR /usr/app
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]