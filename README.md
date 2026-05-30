# users-demo

Demo que importa los usuarios de
[jsonplaceholder.typicode.com/users](https://jsonplaceholder.typicode.com/users)
a una BD H2 al arrancar y los expone vía un endpoint REST protegido con JWT.

## Stack

- Java 17 · Spring Boot 4.0.6 (Spring 7, Security 7)
- Spring Data JPA + H2 en memoria
- `RestClient` (cliente HTTP moderno de Spring)
- JWT con `io.jsonwebtoken` (jjwt) 0.12.6 — HS384
- MapStruct 1.6.3 (con Lombok)
- springdoc-openapi (Swagger UI)
- JUnit 5 + Mockito

## Ejecutar el siguiente comando para levantar el proyecto en local

```bash
  ./mvnw spring-boot:run
```

La app levanta en `http://localhost:8080`. Al arrancar:

1. Hibernate crea las tablas `users`, `addresses`, `companies`.
2. `JsonPlaceholderClient.fetchUsers()` trae los 10 usuarios del API público y los guarda en la BD H2.

## Credenciales para el login

| user | password |
|---|---|
| `admin` | `admin123` |

```bash
  TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
    -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"admin123"}' | jq -r '.token')
```

Ejemplo respuesta: `{ "token": "...", "expiresInMinutes": 60 }`.

## Prueba del endpoint /users protegido

```bash
  curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/users
```

Devuelve un array de 10 objetos `UserResponse` con `address` y `company`
anidados.

## Endpoints

| Método | Path | Auth | Descripción |
|---|---|---|---|
| `POST` | `/auth/login` | público | Devuelve `{token, expiresInMinutes}` |
| `GET` | `/users` | **JWT** | Lista los 10 usuarios |
| `GET` | `/actuator/health` | público | Healthcheck (con detalles) |
| `GET` | `/actuator/info` | público | Metadatos de la app |
| `GET` | `/v3/api-docs` | público | OpenAPI 3 spec en JSON |
| `GET` | `/swagger-ui.html` | público | Swagger UI |
| `GET` | `/h2-console/` | público | Consola H2 (sólo dev) |

### H2 console

Abrir `http://localhost:8080/h2-console/` y conectar con:

- JDBC URL: `jdbc:h2:mem:usersdb`
- User: `sa`
- Password: *(vacío)*

Tablas: `USERS`, `ADDRESSES`, `COMPANIES`.

### Swagger UI

1. Abrir `http://localhost:8080/swagger-ui.html`.
2. Click en **Authorize** (icono de candado).
3. Pegar el token obtenido en `/auth/login`.
4. Probar `GET /users` desde la UI.

## Docker

```bash
  docker build -t users-demo .
  docker run --rm -p 8080:8080 users-demo
```
