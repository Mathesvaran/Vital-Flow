# Vital Flow

Initial authentication and profile setup for the VitalFlow blood management platform.

## Project structure

- `backend`: Spring Boot, Java 21, Maven, PostgreSQL, JWT foundation
- `frontend`: React, Vite, Tailwind CSS, React Router

## Run the backend

1. Create a PostgreSQL database named `vitalflow`.
2. Update the environment variables if your local credentials differ.
3. Run:

```bash
cd backend
mvn spring-boot:run
```

The API starts on `http://localhost:8080`.

## Run the frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend starts on `http://localhost:5173`.

## Initial API endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`

Registration accepts `fullName`, either `email` or `phoneNumber`, `password`, and a non-admin `role`.
