#etapa1: compilacion
FROM openjdk:17.0.2 AS builder
WORKDIR /app
#copiar lo necesario para instalar las depenedencias
COPY ./.mvn .mvn/
COPY ./mvnw .
COPY ./pom.xml .
#descargar dependencias en cache
RUN ./mvnw dependency:go-offline
#copiar el codigo fuente y compilar
COPY ./src ./src
RUN ./mvnw clean package -DskipTests
#RUN ./mvnw clean package -Dskip.test.skip
#etapa 2: imagen de produccion
FROM openjdk:17.0.2
WORKDIR /app
#copiar ek jar desde la etapa de compilacion
COPY --from=builder /app/target/equipos-0.0.1-SNAPSHOT.jar .
#exponer el puerto
# Copiar el archivo .env
COPY .env .env
EXPOSE 8001
#comando de inicio
ENTRYPOINT ["java", "-jar", "equipos-0.0.1-SNAPSHOT.jar"]
#docker build -t equipos:latest .
