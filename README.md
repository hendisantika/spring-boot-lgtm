# Spring Boot LGTM (Logging, Grafana, Tempo, Metrics)

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-lgtm/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-lgtm/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.10.0-C71A36?logo=apachemaven)
[![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)](.github/dependabot.yml)

A demo Spring Boot application showcasing integration with the Grafana observability stack for comprehensive monitoring
and observability.

## Overview

This project demonstrates how to implement a complete observability solution for a Spring Boot application using the
Grafana stack:

- **L**ogging: Centralized log aggregation with Loki
- **G**rafana: Visualization dashboards for metrics, logs, and traces
- **T**empo / Jaeger: Distributed tracing (Jaeger is enabled by default in `compose.yaml`; Tempo config is kept as an
  alternative)
- **M**etrics: Application metrics collection with Prometheus

The application itself is a simple REST API for managing items, with CRUD operations and H2 in-memory database.

## Technologies

- **Java 25** (LTS)
- **Spring Boot 4.1.1**
- **Maven 3.10.0** (via Maven Wrapper)
- **Spring Data JPA** with H2 Database
- **Docker** and Docker Compose for containerization
- **Grafana Stack**:
  - Grafana for visualization
  - Prometheus for metrics collection
  - Jaeger (or Tempo) for distributed tracing
  - Loki for log aggregation (via `loki-logback-appender`)
- **Micrometer** for application metrics and tracing (Brave bridge + Zipkin reporter)
- **GitHub Actions** for CI and **Dependabot** for automated dependency updates

## Prerequisites

- Java 25 or later (e.g. [Eclipse Temurin 25](https://adoptium.net/))
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
docker compose up -d
```

This will start:

- The Spring Boot application on port 8080
- Grafana on port 3000
- Prometheus on port 9090
- Jaeger UI on port 16686 (collectors: OTLP 4317/4318, Zipkin 9411)
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

### Jaeger / Tempo

Traces are exported over the Zipkin protocol (port 9411). By default they are collected by Jaeger and can be explored at
http://localhost:16686. A Tempo configuration is also provided in `docker/tempo` — uncomment the `tempo` service in
`compose.yaml` (and comment out `jaeger`) to use Tempo and view traces in Grafana.

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
- `nginx`: NGINX reverse-proxy configs for Grafana, Prometheus, Loki and Tempo
- `.github/workflows/maven.yml`: CI pipeline
- `.github/dependabot.yml`: Dependabot configuration

## Building the Application

```bash
./mvnw clean package
```

The build compiles and tests against Java 25. Make sure `JAVA_HOME` points to a JDK 25 installation.

## Continuous Integration

GitHub Actions (`.github/workflows/maven.yml`) runs on every push and pull request to `main` (and can be triggered
manually):

- Sets up **Temurin JDK 25** with Maven dependency caching
- Builds and runs tests with `./mvnw -B verify`
- On pushes to `main` only, submits the Maven dependency graph to GitHub to improve Dependabot security alerts

## Dependency Management

Dependabot (`.github/dependabot.yml`) keeps the project up to date:

| Ecosystem        | Scope                                          | Schedule       | Grouping                                             |
|------------------|------------------------------------------------|----------------|------------------------------------------------------|
| `maven`          | `pom.xml` and Maven Wrapper                    | Daily, 05:00   | Spring Boot, Micrometer/Zipkin, other minor & patch  |
| `github-actions` | Workflow actions                               | Daily, 05:00   | All actions in one PR                                |
| `docker`         | `Dockerfile` base images (JDK 25)              | Weekly, Monday | Major Java image bumps ignored to stay on JDK 25     |
| `docker-compose` | `compose.yaml` images (Prometheus, Jaeger, …)  | Weekly, Monday | Whole observability stack in one PR                  |

All times are in `Asia/Jakarta`. Pull requests are labelled and use Conventional Commit prefixes
(`build(deps)`, `ci(deps)`, `build(docker)`, `build(compose)`).

## Docker Build

The application uses a multi-stage Docker build for efficiency: `maven:3.9.11-eclipse-temurin-25` to build and
`amazoncorretto:25-alpine-jdk` to run:

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