# Books Universe Project

## MicroProfile Application with 2 microservices:
- books-universe
- books-universe-invoker

### Model Classes:
- Author
- Book
- Library


### CRUD operations components & REST services
- Entities
- DTO
- Controllers
- Repositories
- Services

### Relational Database: PostgreSQL


### An original algorithm for obtaining the most profitable books for a library with a specified budget
- [BestBooksForLibraryAlgorithm](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/service-b/src/main/java/com/uaic/Lab9/algorithm/BestBooksForLibraryAlgorithm.java) is an algorithm which has as input an id of a library (which has a specified budget) and a list o books. The algorithm outputs a list of books (each book having a price and a review - min. 1, max 5) from this list of books  which this library can afford and are the most profitable (the ratio between price and review is the minimum).

### [UnitTests](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/service-b/src/test/java/controllers/BookControllerTest.java) for BookController


### Deployment using [DockerDesktop](https://www.docker.com/products/docker-desktop)
- Created Docker containers for the microservices, also for the database.
- Docker commands used:
  - database: docker-compose up
  - service-b: 
    - docker build -t books-universe . 
    - docker run -dp 9091:9091 --name books-universe --link books-universe-db --net database_default books-universe
  - service-a:
    - docker build -t books-universe-invoker .
    - docker run -dp 9090:9090 --link books-universe --name books-universe-invoker --net database_default books-universe-invoker

## UML Diagram - Classes
![Class-Diagram](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/diagrams/Class-Diagram.png?raw=true)



## Use-Case: Book
![Use-Case1](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/diagrams/use-cases/Use-Case_Book.png?raw=true)


## Use-Case: Author
![Use-Case2](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/diagrams/use-cases/Use-Case_Author.png?raw=true)


## Use-Case: Library
![Use-Case3](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/diagrams/use-cases/Use-Case_Library.png?raw=true)

## Tests
![Tests](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/unit_tests/unit-tests.png?raw=true)

## SwaggerUI Endpoints
![Endpoints](https://github.com/DianaCrainic/Java-Technologies/blob/main/Project/resources/diagrams/SwaggerUI-endpoints?raw=true)
