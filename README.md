# RecipeApp

RecipeApp is a Spring Boot application designed to manage recipes. It integrates with MySQL, Redis, Elasticsearch, and Kafka to provide a robust and scalable solution for recipe management.

---

## Features

- **Recipe Management**: Create, read, update, and delete recipes.
- **Caching**: Uses Redis for caching recipes to improve performance.
- **Search**: Integrates with Elasticsearch for full-text search capabilities.
- **Event Streaming**: Uses Kafka for real-time event streaming.
- **Scalable Architecture**: Built with Spring Boot and supports microservices architecture.

---

## Technologies Used

- **Backend**: Spring Boot
- **Database**: MySQL
- **Caching**: Redis
- **Search**: Elasticsearch
- **Event Streaming**: Kafka
- **Build Tool**: Maven
- **Logging**: SLF4J with Logback

---

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java**: JDK 17 or higher
- **MySQL**: Version 8.0 or higher
- **Redis**: Version 6.0 or higher
- **Elasticsearch**: Version 7.x or higher
- **Kafka**: Version 3.x or higher
- **Maven**: Version 3.6 or higher

---

## Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/Yogender21505/recipeapp.git
cd recipeapp
```

### Configure Environment Variables
Create a `.env` file in the root directory with the following content:

```properties
SPRING_APPLICATION_NAME=recipeapp

# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/recipeapp
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=Test@1234
SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# Redis Configuration
REDIS_TTL=10
SPRING_DATA_REDIS_HOST=localhost
SPRING_DATA_REDIS_PORT=6379

# Hibernate Configuration
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect

# Elasticsearch Configuration
SPRING_ELASTICSEARCH_REST_URIS=http://localhost:9200

# Kafka Configuration
SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# Kafka Streams Configuration
SPRING_KAFKA_STREAMS_APPLICATION_ID=tag-stream-processor
SPRING_KAFKA_STREAMS_STATE_DIR=/tmp/kafka-streams

# Debugging and Logging
DEBUG=true
LOGGING_LEVEL_ROOT=DEBUG
LOGGING_LEVEL_COM_YOGENDER_RECIPEAPP=DEBUG
```

### Build the Application
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

---

## API Endpoints

### Recipe Management
- **Create Recipe**: `POST /api/recipe`
- **Get All Recipes**: `GET /api/recipes`
- **Get Recipe by ID**: `GET /api/recipe`
- **Update Recipe**: `PUT /api/recipe`
- **Delete Recipe**: `DELETE /api/recipe`

### Example Request for `GET /api/recipe`
```json
{
  "id": 3
}
```
---
### User Management
- **Register User**: `POST /api/user/registeration`
- **User login**: `GET /api/user/login`
- **Check User validation**: `GET /api/user/check`


### Example Request for `POST /api/user/registeration`
```json
{
  "username": "Yogender",
  "password": "123@123",
  "email": "y@gmail.com"
}
```


### Example Request for `GET /api/user/login`
```json
{
  "username": "Yogender",
  "password": "123@123"
}
```
### Example Request for `GET /api/user/check`
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJZb2dlbmRlciIsImV4cCI6MTc1MTQ1NDQ5M30.r0lKbR-Sy2OlK6oArA3IYIKWuVuK5-qvQseLqfiKKk3PGRL8nUsYetud1X5TKADhvh9Nv-ANcED3uuNxeyYt4A"
}
```

---
### Favourite Recipe Management
- **Mark Favourite recipe**: `POST /api/user/updatefavouriterecipes`
- **Get Favourite recipe**: `GET /api/user/favouriterecipes`


### Example Request for `POST /api/user/updatefavouriterecipes`
```json
{
  "userid": 3,
  "recipeid":3,
  "token": "<token of user>"
}
```


### Example Request for `GET /api/user/favouriterecipes`
```json
{
  "userid": 1,
  "token": "<token of user>"
}
```


---

## Debugging

To enable detailed logs, set the following in your `.env` file:
```properties
DEBUG=true
LOGGING_LEVEL_ROOT=DEBUG
LOGGING_LEVEL_COM_YOGENDER_RECIPEAPP=DEBUG
```

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

For any inquiries or support, feel free to reach out:

- **GitHub**: [Yogender21505](https://github.com/Yogender21505)
- **Email**: kumaryogender2004@gmail.com
