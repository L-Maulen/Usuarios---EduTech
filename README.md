# Usuarios EduTech

Servicio backend para la gestión de usuarios, desarrollado con Spring Boot, JPA, Docker y MySQL.  
Incluye consumo del microservicio de cursos, documentación Swagger/OpenAPI, pruebas unitarias, carga automática de datos y soporte HATEOAS.

---

## Características

- CRUD de usuarios y tipos de usuario
- Consumo de microservicio externo de cursos (`cursoEdutech.api.url`)
- Documentación interactiva con Swagger UI (`/doc/swagger-ui.html`)
- Pruebas unitarias con JUnit y Mockito
- Carga automática de datos de ejemplo con DataLoader (perfil dev)
- Endpoints HATEOAS para usuarios (`/api/v2/usuarios`)
- Contenedores Docker y despliegue automatizado en AWS EC2

---

## Estructura del proyecto

- `src/main/java/com/eduDB/EduTech_DB/` - Código fuente principal
- `src/main/resources/` - Archivos de configuración (`application.properties`, etc.)
- `src/test/java/com/eduDB/EduTech_DB/` - Pruebas unitarias
- `Dockerfile` - Imagen Docker para despliegue
- `README.md` - Documentación del proyecto

---

## Configuración

### Variables de entorno

Configura la URL del microservicio de cursos en tu `application.properties`:

```cursoEdutech.api.url=http://<IP-DEL-CURSO>:<PUERTO>/api/v1/cursos/```


### Perfiles de Spring

- `dev`: Desarrollo local (carga datos de ejemplo con DataLoader)
- `test`: Pruebas unitarias

---

## Ejecución local

```sh
./mvnw spring-boot:run
```

O con Docker:
```
docker build -t usuarios-edutech .
docker run -d --name usuarios-edutech -p 8080:8080 usuarios-edutech
```

---

## Endpoints principales

- **Usuarios:** `/api/v1/usuarios`
- **Tipos de usuario:** `/api/v1/TiposUsuarios`
- **Cursos (consumo externo):** `/api/v1/usuarios/cursos-todos`
- **Swagger UI:** [http://localhost:8080/doc/swagger-ui.html](http://localhost:8080/doc/swagger-ui.html)
- **Usuarios HATEOAS:** `/api/v2/usuarios`

---

## Consumo de microservicio de cursos

El endpoint `/api/v1/usuarios/cursos-todos` consume el microservicio de cursos configurado en `cursoEdutech.api.url` usando `RestTemplate` y el DTO `CursoResponse`.


Autor: Luis Maulen