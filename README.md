# Agro Services Platform - Backend

A Spring Boot based backend application for managing agricultural service bookings, providers, users, and notifications.

---

## **Features Implemented**

### **1. Entities**
- **AgroUser**
  - Attributes: `id`, `name`, `email`, `password`, `phone`, `role (ENUM)`, `address`
  - Used for managing users like Admin, Service Providers, and Customers.
- **AgroServiceProvider**
  - Attributes: `id`, `name`, `companyName`, `contactInfo`, `email`
  - Handles agricultural service providers and their offered services.
- **AgroService**
  - Attributes: `id`, `name`, `description`, `price`, `provider_id`
  - Represents agricultural services offered by providers.
- **AgroBooking**
  - Attributes: `id`, `status (ENUM)`, `bookingTime`, `user_id`, `service_id`
  - Represents bookings made by users for services.
- **AgroNotification**
  - Attributes: `id`, `message`, `sentAt`, `booking_id`, `farmer_id`
  - Handles notification records for users.

---

### **2. DTOs (Data Transfer Objects)**
- **AgroUserRequestDTO** → For creating and updating users (with validation annotations).
- **ProviderRequestDto** → For creating service providers.
- **CreationResponseDto** → Standard success response message.
- *(Similar DTOs can be extended for services and bookings.)*

---

### **3. Controllers**
- **AgroUserController**
  - Endpoints:
    - `POST /users` → Create User
    - `PUT /users/{id}` → Update User
    - `DELETE /users/{id}` → Delete User
    - `GET /users` → Get All Users
- **AgroServiceProviderController**
  - Endpoints:
    - `POST /provider/addprovider` → Add Service Provider
    - `GET /provider/{id}` → Get Provider By ID
- **AgroNotificationController**
  - Endpoints prepared for future notification operations.

---

### **4. Services & Implementations**
- **AgroUserService** & **AgroUserServiceImpl**
  - Handles user CRUD operations.
- **AgroServiceProviderService** & **AgroServiceProviderServiceImpl**
  - Handles provider creation and fetch operations.
- **AgroNotificationService** & **AgroNotificationServiceImpl**
  - Handles notifications logic (future integration with booking events).

---

### **5. Exception Handling**
- **Custom Exceptions**
  - `ApiPostResponseException`
  - `BadRequestException`
  - `ResourceNotFoundException`
- **Global Exception Handler**
  - `GlobalExceptionHandler` using `@ControllerAdvice`
  - Handles:
    - `ApiPostResponseException` → Conflict (409)
    - `BadRequestException` → Bad Request (400)
    - `ResourceNotFoundException` → No Content (204)
    - Generic `Exception` → Internal Server Error (500)

---

### **6. Validation**
- DTOs use **Jakarta Validation** (`@NotBlank`, `@Email`, `@Pattern`, `@Size`) for request payload validation.

---

### **7. Database**
- **MySQL** used as database.
- Mappings:
  - One-to-Many: `AgroServiceProvider → AgroService`
  - Many-to-One: `AgroBooking → AgroService` & `AgroBooking → AgroUser`
  - One-to-One: `AgroNotification → AgroBooking`

---

## **Tech Stack**
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **ModelMapper** (for DTO <-> Entity mapping)
- **MySQL**
- **Lombok** (for boilerplate code reduction)
- **Validation** (`jakarta.validation`)

---

## **Planned Features**
- Notifications triggered on booking and cancellation (via SMS/Push).
- Security Integration (Spring Security + JWT).
- React-based Frontend (in-progress).
- Dashboard with Graphs & Analytics.

---

## **How to Run**
1. Clone this repository.
2. Configure `application.properties` with MySQL credentials.
3. Run `mvn spring-boot:run`.
4. Access Swagger UI at `http://localhost:8080/swagger-ui.html`.

---

