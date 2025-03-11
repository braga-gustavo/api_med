# Medical API - Java & Spring Boot Study Project

## 📌 Overview
This project is a **RESTful API** developed for **educational purposes** to study **Java and Spring Boot**. It provides an efficient way to manage medical records, appointments, and healthcare professionals.

## 🚀 Features
- **Doctor & Patient Registration:** Store structured medical data.
- **Appointment Scheduling:** Create and manage medical appointments.
- **Secure Authentication:** Implemented using JWT.
- **RESTful API Architecture:** Follows best practices for scalability and maintainability.

## 🛠️ Technologies Used
- **Java** (Backend development)
- **Spring Boot** (Framework for building the API)
- **Maven** (Dependency management)
- **MySQL/PostgreSQL** (Database)
- **JWT** (Authentication for secure access)

## ⚙️ Requirements
Before running the project, ensure you have the following installed:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL/PostgreSQL](https://www.mysql.com/ or https://www.postgresql.org/)

## 🚀 How to Run the Project
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/braga-gustavo/api_med.git
cd api_med
```

### 2️⃣ Configure the Database
Edit the `application.properties` file inside `src/main/resources/`, setting up your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Build and Run the Project
```bash
mvn clean install
mvn spring-boot:run
```
The server will be running at `http://localhost:8080`.

## 📌 API Endpoints
- **POST /patients** → Register a new patient.
- **POST /doctors** → Register a new doctor.
- **POST /appointments** → Schedule a new medical appointment.
- **GET /patients/{id}** → Retrieve a patient's details.
- **GET /doctors/{id}** → Retrieve a doctor's details.
- **DELETE /appointments/{id}** → Cancel an appointment.

## 🤝 Contribution
If you wish to contribute, follow these steps:
1. **Fork** the repository.
2. Create a **branch** for your feature (`git checkout -b feature/new-feature`).
3. **Commit** your changes (`git commit -m 'Adding new feature'`).
4. **Push** the branch (`git push origin feature/new-feature`).
5. Open a **Pull Request**.

## 📄 License
This project is open-source and licensed under the **MIT License**.

