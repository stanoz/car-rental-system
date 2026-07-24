# Car Rental System

State Street technical assessment.

## Prerequisites

- Docker Desktop (or another Docker-compatible runtime)
- Java 21
- Node.js 22

## Database

Navigate to:

```text
car-rental-system/backend/docker
```

and run:

```bash
docker compose up
```

This command downloads the PostgreSQL image (if necessary) and starts the database.

The database will be available on:

```text
localhost:5432
```

To populate the database with sample data, navigate to:

```text
car-rental-system/backend/src/main/java/com/state/street/backend/db
```

and run:

```text
SeedDatabase.java
```

## Backend

Navigate to:

```text
car-rental-system/backend/src/main/java/com/state/street/backend
```

and run:

```text
BackendApplication.java
```

The backend starts on:

```text
http://localhost:8080
```

## API

The backend exposes the following endpoints:

- `GET /cars`
- `GET /cars/types`
- `POST /reservations`
- `GET /reservations/{id}`

## Frontend

Navigate to:

```text
car-rental-system/frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will be available at:

```text
http://localhost:5173
```
