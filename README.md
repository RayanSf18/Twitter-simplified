<h1 align="center" style="font-weight: bold;">Twitter Simplified 💻</h1>

<p align="center">
 <a href="#technologies">Technologies</a> • 
 <a href="#getting-started">Getting Started</a> • 
 <a href="#api-endpoints">API Endpoints</a> •
 <a href="#developer">Developer</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
    <b>Twitter Simplified is a backend application designed to simulate a microblogging social network similar to the old Twitter now X, offering basic functionalities such as user authentication, creating and managing tweets, and viewing tweet feeds. This application is built using Spring Boot and follows best practices for security, scalability and maintainability.</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java 17
- MySQL 8.0.39
- Spring Framework  3.3.2
- Spring Web 
- Spring Data JPA
- Spring Security
- Spring OAuth2 Resource Server
- Spring Validation I/O
- Spring DevTools
- Docker 27.0.3
- Docker Compose
- Apache Maven 3.3.2
- Lombok
- GIT 2.34.1
- ProblemDetail (Exceptions)

<h2 id="getting-started">🚀 Getting started</h2>

This section guides you through running the project locally.

<h3>Pre-requisites</h3>

Before you begin, ensure you have the following software installed:

* Java Development Kit (JDK) -  https://www.oracle.com/java/technologies/downloads/
* Maven - https://maven.apache.org/download.cgi
* Docker - https://www.docker.com/
* PostgreSQL - https://www.postgresql.org/download/

**Optional:**
* IDE (Integrated Development Environment) - (e.g., IntelliJ IDEA, Eclipse)

<h3>Running the Project</h3>

1.  **Clone the Repository:**
```
git clone git@github.com:RayanSf18/Twitter-simplified.git
```
2. **Navigate to the Project Directory:**
```
cd twitter-simplified
```
3. **Run MySQL Docker:**
```
cd twitter-simplified

docker-compose up -d
```
4. **Start the Application:**
```
cd twitter-simplified

mvn spring-boot:run
```
5. **Location of application:**
```
http://localhost:8080/
```

<h2 id="api-endpoints">📍 API Endpoints</h2>

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/login</kbd>     | register login [request details](#post-login-user)
| <kbd>POST /tweets</kbd>     | create new tweet [request details](#post-create-tweet)
| <kbd>DELETE /tweets/{tweetId}</kbd>     | delete a specific tweet [response details](#delete-tweet)


| <kbd>GET /users</kbd>     | search all users [response details](#get-users-details)
| <kbd>PUT /users/{userId}</kbd>     | update data for a specific user [request details](#put-user)
| <kbd>DELETE /users/{userId}</kbd>     | delete a specific use [request details](#delete-user)
| <kbd>POST /products</kbd>     | create a new product [request details](#post-product-create)
| <kbd>GET /products/{productId}</kbd>     | search for a specific product [response details](#get-product-detail)
| <kbd>GET /products</kbd>     | search all users [response details](#get-products-details)
| <kbd>PUT /products/{productId}</kbd>     | update data for a specific product [request details](#put-product)
| <kbd>DELETE /products/{productId}</kbd>     | deletes a specific product that has not yet been added to an order. [request details](#delete-product)
| <kbd>POST /categories</kbd>     | create a new category [request details](#post-category-create)
| <kbd>GET /categories</kbd>     | search all categories [response details](#get-categories-details)
| <kbd>PUT /categories/{categoryId}</kbd>     | update data for a specific category [request details](#put-category)
| <kbd>DELETE /categories/{categoryName}</kbd>     | deletes a specific category that has not yet been added to an product [request details](#delete-category)
| <kbd>POST /orders</kbd>     | create a new order [request details](#post-order-create)
| <kbd>GET /orders/{orderId}</kbd>     | search for a specific order [response details](#get-order-detail)
| <kbd>GET /orders</kbd>     | search all orders [response details](#get-orders-details)
| <kbd>POST /orders/{orderId}/items?productId={productId}&quantity={quantity}</kbd>     | add product to order [request details](#post-add-product-to-order)
| <kbd>POST /orders/{orderId}/items/remove?productId={productId}</kbd>     | remove product to order [request details](#post-remove-product-to-order)
| <kbd>POST /orders/{orderId}/payment</kbd>     | add payment to order [request details](#post-add-payment-to-order)
| <kbd>POST /orders/{orderId}/cancel</kbd>     | delete order [request details](#delete-order)

<H3>Endpoints: Auth</h3>
<h4 id="post-login-user">POST /auth/login</h4>

**REQUEST**
```json
{
  "username":"Joe",
  "password": "JoeDoe@123"
}
```
**RESPONSE**
```json
{
	"accessToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ0d2l0dGVyLWJhY2tlbmQiLCJzdWIiOiJkY2E3NWU2OS1lNGIxLTQwYTUtYWJkZi1jNjg1YzhjODZiZWIiLCJleHAiOjE3MjI4NjYyMDcsImlhdCI6MTcyMjg2NTkwNywic2NvcGUiOiJCQVNJQyJ9.",
	"expiresIn": 300
}
```
<H3>Endpoints: Tweet</h3>
<h4 id="post-create-tweet">POST /tweets</h4>

**BEARER TOKEN**
```
"accessToken": eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ0d2l0dGVyLWJhY2tlbmQiLCJzdWIiOiJkY2E3NWU2OS1lNGIxLTQwYTUtYWJkZi1jNjg1YzhjODZiZWIiLCJleHAiOjE3MjI4NjYyMDcsImlhdCI6MTcyMjg2NTkwNywic2NvcGUiOiJCQVNJQyJ9.
```
**REQUEST**
```json
{
	"content": "Hello World"  
}
```
**RESPONSE**
```json
{
 HTTP 200 OK
}
```
<h4 id="delete-tweet">DELETE /tweets/{id}</h4>

**BEARER TOKEN**
```
"accessToken": eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ0d2l0dGVyLWJhY2tlbmQiLCJzdWIiOiJkY2E3NWU2OS1lNGIxLTQwYTUtYWJkZi1jNjg1YzhjODZiZWIiLCJleHAiOjE3MjI4NjYyMDcsImlhdCI6MTcyMjg2NTkwNywic2NvcGUiOiJCQVNJQyJ9.
```

**RESPONSE**
```json
{
 HTTP 200 OK
}
```

<h4 id="put-user">PUT /users/{userId}</h4>

**REQUEST**
```json
{
  "name": "Rayan silva Ferreira",
  "phone": "(55) 5 5555-4444",
  "password":  "desenvolvedor.java.backend"
}
```
**RESPONSE**
```json
{
  "user_id": 1
}
```
<h4 id="delete-user">DELETE /users/{userId}</h4>

**REQUEST**
```json
http://localhost:8080/users/1
```

<h3>Endpoints: Product</h3>
<h4 id="post-product-create">POST /products</h4>

**REQUEST**
```json
{
  "name": "Kingston A2000 SSD 10000",
  "brand": "Kingston",
  "model": "A2000",
  "description": "SSD Kingston A2000 NVMe PCIe, 250GB, até 2000MB/s de leitura.",
  "price": 250.00,
  "imgUrl": "http://example.com/img/kingston_a2000.jpg",
  "categories": [
    "Computers",
    "Storage",
    "Components"
  ]
}

```
**RESPONSE**
```json
{
  "product_id": 1
}
```
<h4 id="get-product-detail">GET /products/{productId}</h4>

**RESPONSE**
```json
{
  "productId": 1,
  "name": "Kingston A2000 SSD 10000",
  "brand": "Kingston",
  "model": "A2000",
  "description": "SSD Kingston A2000 NVMe PCIe, 250GB, até 2000MB/s de leitura.",
  "price": 250,
  "imgUrl": "http://example.com/img/kingston_a2000.jpg",
  "categories": [
    {
      "id": 1,
      "name": "Computers"
    },
    {
      "id": 2,
      "name": "Components"
    },
    {
      "id": 3,
      "name": "Storage"
    }
  ]
}
```
<h4 id="get-products-details">GET /products</h4>

**RESPONSE**
```json
{
  "productId": 1,
  "name": "Kingston A2000 SSD 10000",
  "brand": "Kingston",
  "model": "A2000",
  "description": "SSD Kingston A2000 NVMe PCIe, 250GB, até 2000MB/s de leitura.",
  "price": 250,
  "imgUrl": "http://example.com/img/kingston_a2000.jpg",
  "categories": [
    {
      "id": 1,
      "name": "Computers"
    },
    {
      "id": 2,
      "name": "Components"
    },
    {
      "id": 3,
      "name": "Storage"
    }
  ]
}
others...
```
<h4 id="put-product">PUT /products/{productId}</h4>

**REQUEST**
```json
{
  "name": "Kingston A2000",
  "brand": "Kingston AMD",
  "model": "A2000",
  "description": "SSD Kingston A2000 NVMe PCIe, 250GB, até 2000MB/s de leitura.",
  "price": 250.00,
  "imgUrl": "http://example.com/img/kingston_a2000.jpg",
  "categories": [
    "Computers",
    "Casa"
  ]
}
```
**RESPONSE**
```json
{
  "product_id": 1
}
```
<h4 id="delete-product">DELETE /products/{productId}</h4>

**REQUEST**
```json
http://localhost:8080/users/1
```
<h3>Endpoints: Category</h3>
<h4 id="post-category-create">POST /categories</h4>

**REQUEST**
```json
{
  "name": "Home"
}
```
**RESPONSE**
```json
{
  "category_id": 1
}
```
<h4 id="get-categories-details">GET /categories</h4>

**RESPONSE**
```json
{
  "id": 4,
  "name": "Casa"
}
others...
```
<h4 id="put-category">PUT /categories/{categoryId}</h4>

**REQUEST**
```json
{
  "name": "Electrodomestication"
}
```
**RESPONSE**
```json
{
  "category_id": 1
}
```
<h4 id="delete-categoryr">DELETE /categories/{categoryName}</h4>

**REQUEST**
```json
http://localhost:8080/users/Electrodomestication
```
<H3>Endpoints: Order</h3>
<h4 id="post-order-create">POST /orders</h4>

**REQUEST**
```json
<h4 id="post-user-create">POST /users</h4>

**REQUEST**
```json
{
  "name":"Rayan silva",
  "email": "rayan.dev@gmail.com",
  "phone": "(99) 9 9999-9999",
  "password": "dev.java@"
}
```
**RESPONSE**
```json
{
  "order_id": 1
}
```
<h4 id="get-order-detail">GET /orders/{orderId}</h4>

**RESPONSE**
```json
{
  "orderId": 1,
  "moment": "2024-07-16T04:35:07Z",
  "status": "WAITING_PAYMENT",
  "payment": null,
  "client": {
    "id": 2,
    "name": "Jose",
    "email": "joseSf20@gmail.com",
    "phone": "(66) 9 9548-9538",
    "password": "20d91281-5b69-47f5-b157-ad44036c9853"
  },
  "items": [],
  "total": 0
}
```
<h4 id="get-orders-details">GET /orders</h4>

**RESPONSE**
```json
{
  "orderId": 1,
  "moment": "2024-07-16T04:35:07Z",
  "status": "WAITING_PAYMENT",
  "payment": null,
  "client": {
     "id": 2,
     "name": "Jose",
     "email": "joseSf20@gmail.com",
     "phone": "(66) 9 9548-9538",
     "password": "20d91281-5b69-47f5-b157-ad44036c9853"
   },
   "items": [],
   "total": 0
}
others...
```
<h4 id="post-add-product-to-order">POST /orders/{orderId}/items?productId={productId}&quantity={quantity}</h4>

**REQUEST**
```json
http://localhost:8080/users/1/items?productId=1&quantity=2
```
<h4 id="post-remove-product-to-order">POST /orders/{orderId}/items/remove?productId={productId}</h4>

**REQUEST**
```json
http://localhost:8080/users/1/items/remove?productId=1
```
<h4 id="post-add-payment-to-order">POST /orders/{orderId}/payment</h4>

**REQUEST**
```json
{
  "moment": "2024-07-16T04:35:07Z",
  "paymentMethod": "CARD",
  "amount": 5500.0
}
```
<h4 id="delete-order">POST /orders/{orderId}/cancel</h4>

**REQUEST**
```json
http://localhost:8080/orders/1/cancel
```

<h2 id="developer">👨‍💻 Developer</h2>
<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/127986772?v=4" width="100px;" alt="Rayan Silva Profile Picture"/><br>
        <sub>
          <b>Rayan silva</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">🤝 Contribute</h2>

1. Fork the repository.
2. Create a new branch (git checkout -b feature/AmazingFeature).
3. Make your changes.
4. Commit your changes (git commit -m 'Add some AmazingFeature').
5. Push to the branch (git push origin feature/AmazingFeature).
6. Open a pull request.

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

Enjoy coding! 😄
