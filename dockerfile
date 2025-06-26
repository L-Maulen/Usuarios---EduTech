FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN chmod +x mvnw # Si mvnw está en la raíz del contexto de construcción
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk AS prod
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]