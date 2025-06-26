FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia el script mvnw y el directorio .mvn
COPY mvnw .
COPY .mvn ./.mvn/
# Copia el archivo pom.xml y el directorio src
COPY pom.xml .
COPY src ./src

# Asegúrate de que mvnw sea ejecutable
RUN chmod +x mvnw

# Ahora ejecuta mvnw para compilar
RUN ./mvnw clean package -DskipTests

# Etapa de producción
FROM eclipse-temurin:17-jdk AS prod
WORKDIR /app
# Copia el JAR compilado de la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]