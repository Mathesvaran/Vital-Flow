# Vital-Flow

VitalFlow is a Spring Boot backend for blood donation management with a modular layered architecture.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- MySQL (default) / H2 (tests)

## Modules
- `auth` - registration and login
- `donor` - donor profile management

## API Endpoints
### Auth
- `POST /auth/register`
- `POST /auth/login`

### Donor
- `POST /donors/{userId}/profile`

## Build & Test
```bash
mvn test
mvn package
```
