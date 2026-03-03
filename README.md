# Asset Maintenance Management System

## Overview
A Spring Boot REST API for managing industrial equipment lifecycle and maintenance workflows.
The system allows users to create, retrieve, update, and delete asset records using a clean layered architecture and unit-tested business logic.

---

## Tech Stack

- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- H2 In-Memory Database  
- Lombok  
- JUnit 5  
- Mockito  
- Maven  

---

## Architecture

Layered Architecture:

Controller → Service → Repository → Database

This ensures:
- Clean separation of concerns
- Maintainability
- Testability
- Scalability

---

## Features

- Create Asset (POST)
- Retrieve All Assets (GET)
- Retrieve Asset by ID (GET)
- Update Asset Status (PUT)
- Delete Asset (DELETE)
- Global Exception Handling
- 8 Unit Test Cases using Mockito
- H2 Database Integration

---

## API Base URL

http://localhost:8081/api/assets

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /api/assets | Create a new asset |
| GET | /api/assets | Get all assets |
| GET | /api/assets/{id} | Get asset by ID |
| PUT | /api/assets/{id}/status?status=VALUE | Update asset status |
| DELETE | /api/assets/{id} | Delete asset |

---

## Sample Request (POST)

```json
{
  "name": "Industrial Pump",
  "type": "Mechanical",
  "status": "ACTIVE",
  "lastMaintenanceDate": "2026-03-01",
  "nextMaintenanceDate": "2026-06-01"
}
```

---

## Running the Application

1. Clone the repository
```
git clone https://github.com/<your-username>/springboot-asset-maintenance-api.git
```

2. Navigate to project folder
```
cd springboot-asset-maintenance-api
```

3. Run the application
```
mvn spring-boot:run
```

Application will start on:
http://localhost:8081

---

## H2 Database Console

URL:
http://localhost:8081/h2-console

JDBC URL:
jdbc:h2:mem:assetdb

Username:
sa

Password:
(blank)

---

## Screenshots

### Application Running
![App Running](screenshots/app_running.png)

### Create Asset (POST)
![Post Asset](screenshots/post_asset.png)

### Retrieve Assets (GET)
![Get Assets](screenshots/get_assets.png)

---

## Testing

- 8 Unit test cases implemented
- Service layer tested using Mockito
- All tests passing successfully

---

## Key Highlights

- Clean layered architecture
- RESTful API design
- JPA integration
- Exception handling
- Unit testing
- Agile-inspired incremental development
