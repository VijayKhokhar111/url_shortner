# URL Shortener

A secure URL shortener built with Spring Boot, Spring Security, PostgreSQL, and Thymeleaf. Includes user authentication (login/signup) with CSRF-protected forms.

## Features

- User signup and login with Spring Security
- CSRF protection on all forms
- PostgreSQL database with Spring Data JPA / Hibernate
- Thymeleaf server-rendered UI
- Environment-variable-based configuration (no secrets committed to the repo)

## Tech Stack

- Java
- Spring Boot 3.5
- Spring Security 6
- Spring Data JPA / Hibernate
- PostgreSQL
- Thymeleaf
- Maven

## Getting Started

### Prerequisites

- Java 17+ (or your project's target JDK)
- Maven
- PostgreSQL running locally (or accessible via a connection string)

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/VijayKhokhar111/url_shortner.git
   cd url_shortner
   ```

2. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE urlshortner_db;
   ```

3. Set the required environment variables (used by `application.properties`):

   | Variable      | Description                  |
   |---------------|-------------------------------|
   | `DB_Username` | PostgreSQL username           |
   | `DB_Password` | PostgreSQL password           |

   **Windows (PowerShell):**
   ```powershell
   [System.Environment]::SetEnvironmentVariable("DB_Username","your_db_user","User")
   [System.Environment]::SetEnvironmentVariable("DB_Password","your_db_password","User")
   ```

   **macOS/Linux:**
   ```bash
   export DB_Username=your_db_user
   export DB_Password=your_db_password
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Visit `http://localhost:8080/login` in your browser.

## Configuration

Database connection and other settings are managed in `src/main/resources/application.properties`, using placeholders (`${DB_Username}`, `${DB_Password}`) resolved from environment variables at runtime — no credentials are stored in the repo.

## License

This project currently has no license specified.
