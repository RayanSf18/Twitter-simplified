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
| <kbd>GET /tweets/feed</kbd>     | Get a paginated feed of tweets. [response details](#get-feed-tweets)
| <kbd>POST /users/register</kbd>     | register a new user [request details](#post-register-user)
| <kbd>GET /users</kbd>     | List all users. The user request must be ADMIN. [response details](#get-users)

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
```
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
```
{
 HTTP 204 NO CONTENT
}
```
<h4 id="get-feed-tweets">GET /tweets/feed</h4>

**BEARER TOKEN**
```
"accessToken": eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ0d2l0dGVyLWJhY2tlbmQiLCJzdWIiOiJkY2E3NWU2OS1lNGIxLTQwYTUtYWJkZi1jNjg1YzhjODZiZWIiLCJleHAiOjE3MjI4NjYyMDcsImlhdCI6MTcyMjg2NTkwNywic2NvcGUiOiJCQVNJQyJ9.
```

**RESPONSE**
```json
{
	"feedItems": [
		{
			"tweetId": 2,
			"content": "Hello world!",
			"username": "admin",
			"createdAt": "2024-08-05T16:10:50.564005Z"
		},
		{
			"tweetId": 1,
			"content": "Tweet do admin",
			"username": "admin",
			"createdAt": "2024-08-05T16:10:35.416853Z"
		}
	],
	"page": 0,
	"pageSize": 10,
	"totalPages": 1,
	"totalElements": 2
}
```
<H3>Endpoints: User</h3>
<h4 id="post-register-user">POST /users/register</h4>

**REQUEST**
```json
{
  "username":"Joe",
  "password": "JoeDoe@123"
}
```
**RESPONSE**
```
HTTP 200 OK
```
<h4 id="get-users">GET /users</h4>

**BEARER TOKEN**
```
"accessToken": eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ0d2l0dGVyLWJhY2tlbmQiLCJzdWIiOiJkY2E3NWU2OS1lNGIxLTQwYTUtYWJkZi1jNjg1YzhjODZiZWIiLCJleHAiOjE3MjI4NjYyMDcsImlhdCI6MTcyMjg2NTkwNywic2NvcGUiOiJCQVNJQyJ9.
```

**RESPONSE**
```json
[
	{
		"id": "833fafbb-f114-4c3e-ac9b-ad8ef96479ad",
		"username": "admin",
		"password": "$2a$10$05GH9uOlz7Nm9lsqmOj7xuEDwQb.Y7lLhd31KfsDiBn0d8xeHm.3m",
		"roles": [
			{
				"id": 1,
				"name": "ADMIN"
			}
		]
	},
	{
		"id": "dca75e69-e4b1-40a5-abdf-c685c8c86beb",
		"username": "rayan",
		"password": "$2a$10$WcroBxU0SdKgHsqYnpSRluTQW16.Rlv5XjB2DPWBVVcdb7jIYFohi",
		"roles": [
			{
				"id": 2,
				"name": "BASIC"
			}
		]
	}
]
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
