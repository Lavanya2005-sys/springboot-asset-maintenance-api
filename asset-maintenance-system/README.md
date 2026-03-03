# Asset Maintenance Management System

A **Spring Boot REST API** for managing assets and their maintenance schedules.

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.3 |
| Spring Data JPA | ✅ |
| H2 In-Memory Database | ✅ |
| Lombok | ✅ |
| JUnit 5 + Mockito | ✅ |

---

## 📁 Project Structure

```
asset-maintenance-system/
│
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/assetmaintenance/
    │   │   ├── AssetMaintenanceApplication.java
    │   │   ├── controller/
    │   │   │   └── AssetController.java
    │   │   ├── service/
    │   │   │   ├── AssetService.java
    │   │   │   └── AssetServiceImpl.java
    │   │   ├── repository/
    │   │   │   └── AssetRepository.java
    │   │   ├── model/
    │   │   │   └── Asset.java
    │   │   └── exception/
    │   │       ├── ResourceNotFoundException.java
    │   │       └── GlobalExceptionHandler.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/example/assetmaintenance/
            └── service/
                └── AssetServiceImplTest.java
```

---

## ⚙️ Prerequisites

- **Java 17** installed (e.g., Eclipse Adoptium)
- **Maven 3.x** — or use the Maven bundled with NetBeans

> **Note for Windows users:** If `mvn` is not on your PATH, use the full Maven path.
> See the [Running the App](#-running-the-application) section below.

---

## 🚀 Running the Application

### Option 1 — Using system `mvn` (if on PATH)
```bash
mvn spring-boot:run
```

### Option 2 — Using NetBeans bundled Maven (Windows)
Open **PowerShell** and run:
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot"
$mvn = "C:\Program Files\NetBeans-19\netbeans\java\maven\bin\mvn.cmd"
& $mvn spring-boot:run -f "e:\spring\asset-maintenance-system\pom.xml"
```

### Option 3 — Add Maven to PATH permanently (Recommended)
```powershell
# Run once in PowerShell (Admin) — adds NetBeans Maven to system PATH
$mavenBin = "C:\Program Files\NetBeans-19\netbeans\java\maven\bin"
[System.Environment]::SetEnvironmentVariable("Path", $env:Path + ";$mavenBin", "Machine")
```
Then **restart PowerShell** and use `mvn spring-boot:run` directly.

---

## 🧪 Running Tests

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot"
$mvn = "C:\Program Files\NetBeans-19\netbeans\java\maven\bin\mvn.cmd"
& $mvn clean test -f "e:\spring\asset-maintenance-system\pom.xml"
```

**Expected output:**
```
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## 🌐 API Endpoints

Base URL: `http://localhost:8080`

### Create Asset
```http
POST /api/assets
Content-Type: application/json

{
  "name": "Industrial Pump",
  "type": "Mechanical",
  "status": "ACTIVE",
  "lastMaintenanceDate": "2025-01-15",
  "nextMaintenanceDate": "2025-07-15"
}
```

### Get All Assets
```http
GET /api/assets
```

### Get Asset by ID
```http
GET /api/assets/{id}
```

### Update Asset Status
```http
PUT /api/assets/{id}/status
Content-Type: application/json

{
  "status": "UNDER_MAINTENANCE"
}
```

### Delete Asset
```http
DELETE /api/assets/{id}
```

---

## 🗄️ H2 Database Console

Once the app is running, access the H2 web console:

- **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:assetdb`
- **Username:** `sa`
- **Password:** *(leave blank)*

---

## ❌ Error Responses

All errors are returned as structured JSON:

```json
{
  "timestamp": "2026-03-03T15:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Asset not found with id : '99'",
  "path": "/api/assets/99"
}
```

| HTTP Status | When |
|---|---|
| `201 Created` | Asset created successfully |
| `200 OK` | Request succeeded |
| `204 No Content` | Asset deleted |
| `404 Not Found` | Asset ID does not exist |
| `500 Internal Server Error` | Unexpected server error |

---

## 🏛️ Architecture

```
Client → AssetController → AssetServiceImpl → AssetRepository → H2 DB
                                  ↓
                     GlobalExceptionHandler (404 / 500)
```

---

## 📄 License

This project is for educational/demo purposes.
