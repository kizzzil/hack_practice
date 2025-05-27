# Spring Boot Authentication Demo

This project demonstrates a secure authentication system built with Spring Boot and Kotlin, featuring user registration and login functionality.

## Features

- User Registration with validation
- Secure Password Hashing
- User Authentication
- Protected Dashboard
- Modern, Responsive UI
- PostgreSQL Database Integration
- Spring Security Implementation

## Technologies Used

- Kotlin
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Gradle
- Java 21

## Prerequisites

- JDK 21 or later
- PostgreSQL 15 or later
- Gradle 8.x or later

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd hack_practice
```

2. Configure the database:
   - Create a PostgreSQL database
   - Update `src/main/resources/application.properties` with your database credentials if needed

3. Build the project:
```bash
./gradlew build
```

4. Run the application:
```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`

## Usage

### Registration
1. Navigate to `http://localhost:8080/register`
2. Fill in the registration form with:
   - Username
   - Email
   - Password
   - Password Confirmation
3. Submit the form

### Login
1. Navigate to `http://localhost:8080/login`
2. Enter your credentials
3. After successful login, you'll be redirected to the dashboard

## Security Features

- Password Encryption using BCrypt
- CSRF Protection
- Session Management
- Secure Password Storage
- Form Validation
- SQL Injection Prevention

## Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── config/
│   │               ├── controller/
│   │               ├── dto/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   └── resources/
│       ├── static/
│       │   └── css/
│       └── templates/
└── test/
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License
Free to use but pay for viewing