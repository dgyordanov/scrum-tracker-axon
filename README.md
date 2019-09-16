# scrum-tracker-axon
This project aims to show how to build event sourced applications using the Axon Framework and DDD.
The implemented domain model comes from Implementing DDD book - https://www.youtube.com/watch?v=Xf_aLAK1RfE 
## Tech stack
- Java 11
- Spring boot 2
- Axon Framework
- Maven

## Domain
The domain is a very simple scrum tracking system. It allows the users to create _Backlog Items_ and schedule them in _Sprints_ .

## New Java features to practice and use
- The new var keyword - java 10
- Stream enhancements - java 9
  - Lists.of(...)
  - takeWhile(), dropWhile()