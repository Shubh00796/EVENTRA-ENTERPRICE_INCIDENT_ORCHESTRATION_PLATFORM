FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x wait-for-mysql.sh && ./mvnw clean package -DskipTests

COPY target/EVMP-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["./wait-for-mysql.sh", "mysql-db", "3306", "java", "-jar", "app.jar"]
