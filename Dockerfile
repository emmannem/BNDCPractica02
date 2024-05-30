# Usar una imagen base de OpenJDK 11
FROM openjdk:11.0.16

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos JAR al directorio de trabajo
# El ARG JAR_FILE se utiliza para especificar el archivo JAR de la aplicación
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Copiar el archivo de propiedades al directorio de trabajo
# El ARG PROP_FILE se utiliza para especificar el archivo de propiedades de la aplicación
ARG PROP_FILE=src/main/resources/application.properties
COPY ${PROP_FILE} prop.properties

# Exponer el puerto en el contenedor
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
# Se utiliza ENTRYPOINT para ejecutar el JAR de la aplicación con el archivo de propiedades externo
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.location=file:/app/prop.properties"]
