# Spring Boot LGTM (Logging, Grafana, Tempo, Metrics)

A demo Spring Boot application showcasing integration with the Grafana observability stack for comprehensive monitoring
and observability.

## Overview

This project demonstrates how to implement a complete observability solution for a Spring Boot application using the
Grafana stack:

- **L**ogging: Centralized log aggregation with Loki
- **G**rafana: Visualization dashboards for metrics, logs, and traces
- **T**empo: Distributed tracing
- **M**etrics: Application metrics collection with Prometheus

The application itself is a simple REST API for managing items, with CRUD operations and H2 in-memory database.

## Technologies

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA** with H2 Database
- **Docker** and Docker Compose for containerization
- **Grafana Stack**:
  - Grafana for visualization
  - Prometheus for metrics collection
  - Tempo for distributed tracing
  - Loki for log aggregation
- **Micrometer** for application metrics and tracing

## Prerequisites

- Java 21 or later
- Docker and Docker Compose
- Maven (or use the included Maven wrapper)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/hendisantika/spring-boot-lgtm.git
cd spring-boot-lgtm
```

### Run with Docker Compose

The easiest way to run the application with the complete observability stack is using Docker Compose:

```bash
docker-compose up -d
```

This will start:

- The Spring Boot application on port 8080
- Grafana on port 3000
- Prometheus on port 9090
- Tempo on ports 3200 and 9411
- Loki on port 3100

### Run Locally (Development)

To run the application locally without Docker:

```bash
./mvnw spring-boot:run
```

Note: When running locally, you'll need to adjust the configuration to connect to the observability services if they're
running in Docker.

## Usage

### API Endpoints

- **GET /api/items**: Retrieve all items

### H2 Console

The H2 database console is available at:

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## Observability Features

### Grafana

Access Grafana at http://localhost:3000 with the following credentials:

- Username: `yu71`
- Password: `53cret`

Pre-configured dashboards are available for:

- Application metrics
- Logs
- Distributed traces

### Prometheus

Access Prometheus at http://localhost:9090 to query metrics directly.

### Tempo

Tempo is configured to collect distributed traces from the application. Traces can be viewed in Grafana.

### Loki

Loki collects logs from the application. Logs can be queried in Grafana.

## Project Structure

- `src/main/java`: Java source code
  - `controller`: REST controllers
  - `service`: Business logic
  - `repository`: Data access
  - `entity`: JPA entities
- `src/main/resources`: Application resources
  - `application.yml`: Application configuration
  - `logback-spring.xml`: Logging configuration
  - `data.sql`: Initial database data
- `docker`: Docker configuration files
  - `grafana`: Grafana configuration and dashboards
  - `prometheus`: Prometheus configuration
  - `tempo`: Tempo configuration

## Building the Application

```bash
./mvnw clean package
```

## Docker Build

The application uses a multi-stage Docker build for efficiency:

```bash
docker build -t spring-boot-lgtm .
```

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Hendi Santika

- Email: hendisantika@yahoo.co.id
- Telegram: @hendisantika34
- Website: [s.id/hendisantika](https://s.id/hendisantika)