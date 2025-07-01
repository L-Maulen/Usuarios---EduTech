# Usuarios EduTech API

Microservicio para la gestión de usuarios en EduTech, con integración para consumir cursos desde otro microservicio.  
Incluye documentación Swagger, soporte HATEOAS, carga de datos automática, pruebas unitarias y consumo de microservicios.

---

## 🚀 Tecnologías

- Java 17+
- Spring Boot
- Spring Data JPA
- Docker
- Swagger/OpenAPI
- HATEOAS
- JUnit y Mockito para testing

---

## 📦 Endpoints principales

### Usuarios

- **GET `/api/v1/usuarios`**  
  Lista todos los usuarios registrados.

- **GET `/api/v1/usuarios/{id}`**  
  Obtiene un usuario por su ID.

- **POST `/api/v1/usuarios`**  
  Crea un nuevo usuario.

- **PUT `/api/v1/usuarios/{id}`**  
  Actualiza un usuario existente.

- **DELETE `/api/v1/usuarios/{id}`**  
  Elimina un usuario por su ID.

### Tipos de Usuario

- **GET `/api/v1/TiposUsuarios`**  
  Lista todos los tipos de usuario.

- **GET `/api/v1/TiposUsuarios/{id}`**  
  Obtiene un tipo de usuario por su ID.

- **POST `/api/v1/TiposUsuarios`**  
  Crea un nuevo tipo de usuario.

- **PUT `/api/v1/TiposUsuarios/{id}`**  
  Actualiza un tipo de usuario.

- **DELETE `/api/v1/TiposUsuarios/{id}`**  
  Elimina un tipo de usuario.

### Consumo de microservicio de cursos

- **GET `/api/v1/usuarios/cursos-todos`**  
  Devuelve la lista de todos los cursos disponibles, consumiendo el microservicio de cursos externo.

---

## 🔗 Integración con microservicio de cursos

- La URL del microservicio de cursos se configura en `application.properties`:

- El consumo se realiza usando `RestTemplate` y un DTO (`CursoResponse`) que mapea la respuesta del microservicio de cursos.

---

## 📝 Funcionalidades adicionales

### Documentación Swagger

- La API está documentada con Swagger/OpenAPI.
- Accede a la documentación interactiva en:  