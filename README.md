# Agro Services Platform - Backend

A **Spring Boot based backend** application for managing **agricultural service bookings, providers, users, and notifications**.

---

## **📦 Features Implemented**

### **1. Entities**
AgroUser (Admin / Provider / Customer)
│
├── id, name, email, password, phone, role, address
│
AgroServiceProvider
│
├── id, name, companyName, contactInfo, email
│
AgroService
│
├── id, name, description, price, provider_id
│
AgroBooking
│
├── id, status, bookingTime, user_id, service_id
│
AgroNotification
├── id, message, sentAt, booking_id, farmer_id

markdown
Copy
Edit
- **AgroUser** – Manages Admin, Service Providers, and Customers.  
- **AgroServiceProvider** – Handles agricultural service providers and their offered services.  
- **AgroService** – Represents agricultural services offered by providers.  
- **AgroBooking** – Represents bookings made by users for services.  
- **AgroNotification** – Handles notification records for users.

---

### **2. DTOs (Data Transfer Objects)**
AgroUserRequestDTO → Used for user create/update (validated)
ProviderRequestDto → Used for provider create/update
ApiResponseDto → Standard API response format

yaml
Copy
Edit
*(Similar DTOs can be extended for services and bookings.)*

---

### **3. Controllers**
[AgroUserController]
├── POST /users → Create User
├── PUT /users/{id} → Update User
├── DELETE /users/{id} → Delete User
└── GET /users → Get All Users

[AgroServiceProviderController]
├── POST /provider/addprovider → Add Service Provider
└── GET /provider/{id} → Get Provider By ID

[AgroNotificationController]
└── (Future notification endpoints)

yaml
Copy
Edit

---

### **4. 🛠️ Services & Implementations**
AgroUserService → AgroUserServiceImpl
└─ Handles CRUD operations for users

AgroServiceProviderService → AgroServiceProviderServiceImpl
└─ Handles provider creation and fetch operations

AgroNotificationService → AgroNotificationServiceImpl
└─ Handles notifications (future integration with booking events)

yaml
Copy
Edit

---

### **5. ⚠️ Exception Handling**
Custom Exceptions:
├── ApiPostResponseException
├── BadRequestException
└── ResourceNotFoundException

GlobalExceptionHandler (@ControllerAdvice):
├── 409 → Conflict
├── 400 → Bad Request
├── 204 → No Content
└── 500 → Internal Server Error

yaml
Copy
Edit

---

### **6. ✅ Validation**
DTO Validations (Jakarta):
├── @NotBlank
├── @Email
├── @Pattern
└── @Size

yaml
Copy
Edit

---

### **7. 🗄️ Database**
MySQL Relational Mappings:
├── One-to-Many : AgroServiceProvider → AgroService
├── Many-to-One : AgroBooking → AgroService
AgroBooking → AgroUser
└── One-to-One : AgroNotification → AgroBooking

yaml
Copy
Edit

---

### **📨 Booking → Notification Flow**
User Books Service
│
▼
Booking Entity
│
Trigger Notification Event
│
▼
Notification Entity (message, sentAt)
│
Send SMS / Push (Planned)

yaml
Copy
Edit

---

### **🖥️ Overall System Flow**
┌────────────┐ ┌──────────────┐ ┌─────────────┐
│ Client │ ---> │ Controller │ ---> │ Service │
└────────────┘ └──────────────┘ └─────┬───────┘
│
┌───────▼────────┐
│ Repository │
└───────┬────────┘
│
┌───────▼────────┐
│ MySQL DB │
└────────────────┘

yaml
Copy
Edit

---

## **🛠 Tech Stack**
Java 17
Spring Boot 3.x
Spring Data JPA
ModelMapper (DTO <-> Entity mapping)
MySQL
Lombok (boilerplate reduction)
Jakarta Validation

yaml
Copy
Edit

---

## **🚀 Planned Features**
Notifications triggered on booking and cancellation (via SMS/Push)

Security Integration (Spring Security + JWT)

React-based Frontend (in-progress)

Dashboard with Graphs & Analytics

yaml
Copy
Edit

---

## **▶ How to Run**
Clone this repository.

Configure application.properties with MySQL credentials.

Run mvn spring-boot:run.

Access Swagger UI at http://localhost:8080/swagger-ui.html

yaml
Copy
Edit

---

## **📄 License**
Distributed under the **MIT License**. See `LICENSE` for details.
