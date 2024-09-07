# Coding Challenge: API for Product Management

The Coding Interview for Graduates project at Prospecta involves developing APIs with JWT authentication and role-based access control, using the Fake Store API. The project requires creating two APIs: one for listing product details by category and another for adding new product entries. Additionally, it includes a theoretical challenge where a program processes a CSV file containing values and formulas, calculates results, and generates a CSV output. The tech stack implemented includes Java with Spring Boot for API development, JWT for authentication, role-based access control to secure endpoints, and Java for CSV processing. The implementation also emphasizes robust error handling and validation to manage formulas, cell references, and non-integer values effectively.


## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- MySQL
- Postman (for API testing)
- Swagger
- STS OR Intellij

### Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/Akash1yadavv/Prospecta_Backenmd_Assignment
    cd Prospecta_Assignment
    ```

2. **Create and Configure MySQL Database:**

    - Create a new MySQL database.
    - Update the `src/main/resources/application.properties` file with your database credentials:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/prospecta
      spring.datasource.username=your_database_username
      spring.datasource.password=your_database_password
      spring.jpa.hibernate.ddl-auto=update
      
      ```

3. **Build and Run the Application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

#### 1. Sign Up

- **Endpoint:** `POST http://localhost:8080/api/prospecta/register-user`
- **Description:** Registers a new user.
- **Request Body:**

    ```json
    {
      "email": "user@example.com",
      "password": "your_password",
      "name": "John Doe",
      "phone": "1234567890"
    }
    ```

#### 2. Log In

- **Endpoint:** `POST http://localhost:8080/api/prospecta/auth/login`
- **Description:** Authenticates a user and returns a JWT token.
- **Request Body:**

    ```json
    {
      "email": "user@example.com",
      "password": "your_password"
    }
    ```

- **Response:**

    ```json
    {
      "token": "your_jwt_token",
      "expiresIn": "time_in_milli_second"
    }
    ```

### Product Management

#### 1. Get Products by Category

- **Endpoint:** `GET http://localhost:8080/api/prospecta/products/category/{category}`
- **Description:** Retrieves a list of products based on the specified category.
- **Parameters:**
  - `category`: The category of the products (e.g., `jewelery`).
- **Response:**

    ```json
    [
      {
        "id": 1,
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg",
        "rating": {
          "rate": 4.5,
          "count": 120
        }
      }
      // More products
    ]
    ```

#### 2. Add New Product

- **Endpoint:** `POST http://localhost:8080/api/prospecta/products/`
- **Description:** Adds a new product to the store.
- **Request Body:**

    ```json
      {
        "id": 1,
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg",
        "rating": {
          "rate": 4.5,
          "count": 120
        }
      }
    ```

- **Response:**

    ```json
      {
        "id": 21,
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg",
    
      }
    ```

### Theoretical Challenge

#### 1. Upload CSV File and get proper result
- **Description:** upload csv file like `https://github.com/Akash1yadavv/Prospecta_Backenmd_Assignment/blob/main/SampleTestInput.csv`
- **Request Body:**

    ```
     Multipart/form-data
     CSV File
    ```

- **Response:**

    ```
      5,3,10
      7,8,15
      9,9,24
    ```

## How to Test

**Open Browser And paste this URL `http://localhost:8080/swagger-ui/index.html`  **

**OR**

1. **Sign Up:**

    Use Postman to send a `POST` request to `http://localhost:8080/api/prospecta/register-user` with the required body parameters.

2. **Log In:**

    Send a `POST` request to `http://localhost:8080/api/prospecta/auth/login` to receive the JWT token.

3. **Get Products:**

    Send a `GET` request to `http://localhost:8080/api/prospecta/products/category/{category}` with the appropriate category and jwt token in headers.

4. **Add Product:**

    Send a `POST` request to `http://localhost:8080/api/prospecta/products/` with the product details in the request body and jwt token in headers.

5. **Theoretical Challenge CSV File :**   
    Send a `POST` request to `http://localhost:8080/api/prospecta/upload-csv` upload A csv file and with Key= `file` Make sure file should be .CSV file
   
## Security and Reliability

To ensure the security and reliability of the APIs:

- **Authentication:** Use JWT tokens for secure access.
- **Data Validation:** Validate all input data to prevent invalid or harmful data from being processed.
- **Error Handling:** Implement proper error handling to provide meaningful error messages.
- **Logging:** Use logging to track application behavior and debug issues.
- **Rate Limiting:** Consider implementing rate limiting to prevent abuse of the APIs.

## Contributing

Feel free to submit issues or pull requests. Please make sure your contributions adhere to the project's coding standards.

Replace placeholders such as `your-username`, `your-repository`, `your_database_name`, `your_database_username`, and `your_database_password` with your actual values.
