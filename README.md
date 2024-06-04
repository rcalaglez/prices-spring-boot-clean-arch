# E-commerce Price Service

A small service for applicable price queries, with Java, Spring Boot, following Clean Architecture principles. 

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Clone the Repository

```bash
git clone https://github.com/rcalaglez/prices-spring-boot-clean-arch.git
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The application should be running at `http://localhost:8080`

### Running tests

```bash
mvn test
```

## API Documentation

You can access the API documentation using Swagger UI. Follow these steps:

1. Ensure the application is running.
2. Open your web browser and navigate to [http://localhost:8080/webjars/swagger-ui/index.html](http://localhost:8080/webjars/swagger-ui/index.html).
3. In the input field, enter `/api/openapi.yaml` and press "Explore".

This will load the API documentation where you can explore the available endpoints and test them directly from the browser.
