# Vital Flow

VitalFlow is a blood management platform connecting donors, hospitals, and blood banks.

## Requirements

- Java 21
- Maven 3.9+
- Node.js LTS and npm
- PostgreSQL running locally

## Database setup

Create a PostgreSQL database:

```sql
CREATE DATABASE vitalflow;
```

The backend defaults are:

- URL: `jdbc:postgresql://localhost:5432/vitalflow`
- Username: `postgres`
- Password: `postgres`

If your local values differ, set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your environment. `spring.jpa.hibernate.ddl-auto=update` creates and updates the `users` table automatically for local development.

## Run the backend

From the repository root:

```bash
cd backend
mvn spring-boot:run
```

Backend: `http://localhost:8080`

## Run the frontend

In a second terminal:

```bash
cd frontend
npm install
npm run dev
```

Frontend: `http://localhost:5173`

Optional frontend API override:

```bash
VITE_API_BASE_URL=http://localhost:8080/api npm run dev
```

## Working authentication APIs

### Register

`POST http://localhost:8080/api/auth/register`

```json
{
  "fullName": "Alex Johnson",
  "email": "alex@example.com",
  "phoneNumber": "",
  "password": "Password123",
  "role": "DONOR"
}
```

Use `DONOR`, `HOSPITAL`, or `BLOOD_BANK`. Email or phone is required. Public `ADMIN` registration is rejected.

### Login

`POST http://localhost:8080/api/auth/login`

```json
{
  "identifier": "alex@example.com",
  "password": "Password123"
}
```

### Current user

`GET http://localhost:8080/api/auth/me` with:

```text
Authorization: Bearer <token>
```

## UI routes

- `/`
- `/login`
- `/register/donor`
- `/register/hospital`
- `/register/blood-bank`
- `/donor/dashboard`
- `/hospital/dashboard`
- `/blood-bank/dashboard`
