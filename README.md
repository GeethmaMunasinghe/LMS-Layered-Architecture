# Learning Management System (LMS) 

## 📌 Project Overview

This **Learning Management System (LMS)** is a desktop-based application developed using **JavaFX** and **MySQL**. This version of the LMS is implemented using a **Layered Architecture**, focusing on clean separation of responsibilities, scalability, and maintainability.

The project was developed to simulate a real-world institutional system while demonstrating strong knowledge of **software architecture**, **object-oriented design**, and **database-driven desktop applications**.

---

## 🏗️ Architecture – Layered Architecture

This LMS follows a **Layered Architecture**, where each layer has a clearly defined responsibility:

### 🔹 Presentation Layer

* Built using **JavaFX and FXML**
* Handles all user interface components and user interactions
* Contains controllers responsible for capturing user input

### 🔹 Business Layer

* Contains core business logic and validations
* Acts as an intermediary between Presentation and Data Access layers
* Ensures rules and workflows are applied consistently

### 🔹 Data Access Layer (DAO)

* Manages all database-related operations
* Uses **JDBC** to communicate with the MySQL database
* Responsible for CRUD operations

### 🔹 Entity / Model 

* Represents database entities such as Users, Students, Teachers, Programs, and Intakes
* Used to transfer data between layers

This architecture ensures:

* Loose coupling between layers
* Better maintainability and scalability
* Easier debugging and testing
* Improved code readability

---

## 🚀 System Features

### 🔐 Authentication

* User Login
* User Signup / Registration
* Credential validation

### 👨‍🎓 Student Management

* Add, update, delete students
* View student details

### 👩‍🏫 Teacher Management

* Add, update, delete teachers
* View teacher details

### 📚 Program Management

* Create and manage academic programs
* Update and remove programs

### 🗂️ Intake Management

* Create intakes for programs
* View and update intake details

---

## 🛠️ Technologies Used

* **Programming Language:** Java
* **UI Framework:** JavaFX (FXML)
* **Database:** MySQL
* **Architecture Pattern:** Layered Architecture
* **Database Connectivity:** JDBC
* **IDE:** IntelliJ IDEA

---

## 🗄️ Database Design

* MySQL database used for persistent storage
* Normalized tables for users, students, teachers, programs, and intakes
* DAO layer abstracts all database interactions

---

## ▶️ How to Run the Project

1. Clone the repository
2. Open the project in your IDE
3. Configure MySQL database credentials
4. Execute SQL scripts to create required tables
5. Run the main JavaFX application class

---

## 🖼️ System Screenshots

This section contains screenshots of key interfaces and workflows of the LMS.

### 🔐 Authentication

* Login Screen
  
<img width="622" height="537" alt="image" src="https://github.com/user-attachments/assets/c37c65ab-a48f-4817-8363-08bdaf089e8d" />

* Signup Screen
  
<img width="602" height="547" alt="image" src="https://github.com/user-attachments/assets/a66865c9-50ec-4c24-9f71-5115e0915add" />


### 👨‍🎓 Student Management

* Student Management Dashboard
  
  <img width="753" height="537" alt="image" src="https://github.com/user-attachments/assets/fc6fe3b9-031b-4752-a9d6-f823519196bc" />


### 👩‍🏫 Teacher Management

* Teacher Management Interface
  
<img width="751" height="541" alt="image" src="https://github.com/user-attachments/assets/546e4894-479f-4bb9-9b1d-b616313fa3c7" />

### 📚 Program Management

* Program Management Interface
  
<img width="765" height="822" alt="image" src="https://github.com/user-attachments/assets/bcdf012e-6cd0-49d8-ac01-61414e03f7f7" />

### 🗂️ Intake Management

* Intake Management Interface
  
<img width="772" height="538" alt="image" src="https://github.com/user-attachments/assets/ba8b0798-8b38-4ca0-b419-38ce45311692" />

<img width="782" height="331" alt="image" src="https://github.com/user-attachments/assets/4f651abb-1587-4a8f-812c-d52cdd3bbce9" />

