# Microservices ecosystem

<!-- TABLE OF CONTENTS -->
## Table of Contents

- [Table of Contents](#table-of-contents)
- [About The Project](#about-the-project)
  - [Built With](#built-with)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

<!-- ABOUT THE PROJECT -->
## About The Project

Microservices ecosystem boilerplate using Spring Boot, Spring Cloud and Netflix OSS.
This architecture involves a Gateway with Netflix Zuul, a discovery server with Netflix Eureka, a configuration server with Spring Cloud Config
and, an Authorization server with Spring Cloud OAuth2. Circuit breaker with Hystrix and load balancer with Ribbon are implicit with Eureka.

### Built With
Entire project was built with:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud](https://spring.io/projects/spring-cloud)
* [Netflix Zuul](https://github.com/Netflix/zuul)
* [Netflix Eureka](https://github.com/Netflix/eureka)
* [Netflix Hystrix](https://github.com/Netflix/Hystrix)
* [Netflix Ribbon](https://github.com/Netflix/ribbon)

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* Java 14
```sh
java -version
```
To download this java version go to [OpenJDK](https://openjdk.java.net/projects/jdk/14/)

### Installation

1. Clone the repo
```sh
git clone https://github.com/danicaliforrnia/microservices-spring-boot-boilerplate.git
```
2. Download dependencies in POM.

<!-- USAGE EXAMPLES -->
## Usage

### Config Server

Config Server is a centralized configuration service. With this service
you have a central place to manage external properties for applications across all environments.

This project uses a local git repo as properties storage. You can change the local repo setting the url
in the properties file:
```sh
spring.cloud.config.server.git.uri=file:///C:/Sources/personal-projects/microservices-spring-boot-boilerplate/properties
```

You can also use a remote git repo as properties storage. 

### Eureka Server

Eureka Server allows automatic detection of network locations for service instances,
which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.

Eureka Server offers a dashboard where you can see and check the instances for each service. This
dashboard is protected by an in memory user.

Each service registers against Eureka through this URL `http://admin:12345678@localhost:8095/eureka`,
where admin and 12345678 are in memory user and password respectively.

### Zuul Server

Zuul Server is an API Gateway, a single entry point into the system, used to handle requests by
routing them to the appropriate service. Also, Zuul Server acts as a resources server, checking JWT tokens and
securing all the routes.

You can use pre and post filter to add or check data in the request and response.

### Auth Server

Auth Server is an OAuth2 security server, in charge of JWT token generation and user login attempts.
This serve makes use of feign client in order to request user info to Users Service.

### Users Service

Users Service is in charge of users and roles management. Embedded H2 is the database where users, roles and permissions data 
is stored. Access to H2 database console is through `localhost:PORT/api/users/v1/h2-console` with the credentials
at application.properties file.

This server makes used of `errors-spring-boot-starter` library to handle custom exceptions.

Users with USER and ADMIN role can get the list of users. Users with ADMIN role can create, update and
delete users.

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/amazing-feature`)
3. Commit your Changes (`git commit -m 'Add some amazing-feature'`)
4. Push to the Branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact

Daniel Stefanelli - [@danicalifoornia](https://twitter.com/danicalifoornia) - www.danielstefanelli.com -
daniel.stefanelli.h@gmail.com