🏔️ Simple Tour Package API

A Spring Boot REST API for managing tour packages.
Supports CRUD endpoints, validation, error handling, and Swagger UI documentation.

🚀 Features

✅ Create a new tour with validation rules

✅ Fetch all tours with pagination

✅ Fetch a tour by ID

✅ Consistent API response format (success, message, data, errors)

✅ Swagger UI integration (/swagger-ui.html)

✅ Error handling for:

Validation errors

Resource not found

Illegal arguments

Generic exceptions

🛠️ Tech Stack

Java 17+

Spring Boot 3+

Spring Data JPA

Hibernate Validation

MySQL (default, configurable)

Springdoc OpenAPI (Swagger)

📦 Installation & Running Locally
1️⃣ Clone the Repository
git clone https://github.com/RajKTH1415/Simple-Tour-Package-API.git
cd Simple-Tour-Package-API

2️⃣ Configure Database

Default config (application.properties):

spring.application.name=Simple-Tour-Package-API
logging.pattern.console = ${LOGPATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

spring.datasource.url=jdbc:mysql://localhost:3306/tours_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


➡️ Update username/password/URL if needed.
➡️ Alternatively, you can switch to PostgreSQL or H2 by adjusting configs.

3️⃣ Run the Application

Using Maven wrapper:

./mvnw spring-boot:run


Or build & run JAR:

mvn clean package
java -jar target/simple-tour-package-api-0.0.1-SNAPSHOT.jar

4️⃣ Access API

Swagger UI → http://localhost:8080/swagger-ui.html

Base URL → http://localhost:8080/api/tours

📘 API Endpoints
1️⃣ Create Tour — POST /api/tours

Headers

Content-Type: application/json


Body

{
  "title": "Himalayan Trek Adventure",
  "description": "14-day trek through the Himalayas",
  "image": "https://images.unsplash.com/photo-1598275277521-1885382d523a",
  "duration": "14Days/13Nights",
  "actualPrice": "$1200",
  "discountInPercentage": "17%"
}


✅ 201 Response

{
  "success": true,
  "message": "Tour created successfully",
  "data": {
    "id": 1,
    "title": "Himalayan Trek Adventure",
    "description": "14-day trek through the Himalayas",
    "image": "https://images.unsplash.com/photo-1598275277521-1885382d523a",
    "duration": "14Days/13Nights",
    "actualPrice": "$1200.00",
    "discountInPercentage": "17%",
    "discountedPrice": "$996.00",
    "currency": "USD",
    "createdAt": "2025-08-30T11:30:00Z"
  }
}


❌ Negative Tests

Missing title → 400 with validation error

Invalid duration → must be like "14Days/13Nights"

Invalid actualPrice → must be like "$1200" or "$1200.00"

Invalid discount → must be 0%..100%

2️⃣ List Tours — GET /api/tours?page=0&size=10

✅ 200 Response

{
  "success": true,
  "message": "Tours fetched successfully",
  "data": [
    {
      "id": 1,
      "title": "Himalayan Trek Adventure",
      "description": "14-day trek through the Himalayas",
      "image": "https://images.unsplash.com/photo-1598275277521-1885382d523a",
      "duration": "14Days/13Nights",
      "actualPrice": "$1200.00",
      "discountInPercentage": "17%",
      "discountedPrice": "$996.00",
      "currency": "USD",
      "createdAt": "2025-08-30T11:30:00Z"
    }
  ]
}


👉 Try:

?page=1 → likely empty

?size=2 → smaller results

?page=-1&size=0 → expect 400 Bad Request

3️⃣ Get Tour by ID — GET /api/tours/{id}

✅ 200 Response

{
  "success": true,
  "message": "Tour fetched successfully",
  "data": {
    "id": 1,
    "title": "Himalayan Trek Adventure",
    "description": "14-day trek through the Himalayas",
    "image": "https://images.unsplash.com/photo-1598275277521-1885382d523a",
    "duration": "14Days/13Nights",
    "actualPrice": "$1200.00",
    "discountInPercentage": "17%",
    "discountedPrice": "$996.00",
    "currency": "USD",
    "createdAt": "2025-08-30T11:30:00Z"
  }
}


❌ Not Found Example

{
  "success": false,
  "message": "Tour with id=999 not found",
  "errors": []
}

📖 Swagger Documentation

Run the app, then open:
👉 http://localhost:8080/swagger-ui.html

👤 Author

Rajkumar Prasad
📧 rajkumarprasadkth@gmail.com
