# Barcode Validator

Spring Web Application that validates S10 (UPU standard) barcodes.
## Technical Features

### Strategy Pattern
* Each algorithm is encapsulated in its own class, promoting separation of concerns.
* Algorithms can be tested individually.

### Facade Pattern
* Provides a simplified interface to a complex subsystem.
* Clients are decoupled from the subsystem.
* Organises the code by grouping related functionalities into a single class.

#### Assumptions
* The Weights may or may not change, so I stored them in a json file. If they require changes, a complete rebuild and deploy is not necessary.
* I assume the modulus 11 won't change in the check digit algorithm.

## Prerequisites

To run the project you will need to have the following installed

* Java 17
* Docker

# Testing

Open a bash terminal at the project directory and use the following commands to run tests and generate reports.
* Test reports can be found in build>>reports, open the respective index.html files in a browser to view them.
```bash
./gradlew test
````
* Run all JUnit tests.

```bash
./gradlew pitest
````
* Generates pitest report on the line coverage and mutation coverage.

# Running

Open a bash terminal at the project directory and use the following commands to set up your environment.
```bash
./gradlew clean build
````
* Ensures a clean build of the WebApp.

```bash
docker-compose up --build
```
* This will create the docker image, volume and container.

```bash
docker-compose up -d
```
* Start the existing docker images.

```bash
docker-compose down
```
* Stop and remove Docker containers, networks, volumes, and images created by the docker-compose script.
```bash
./gradlew bootRun
```
* Run the WebApp locally and expose the Endpoints.

# Endpoints

* Port ``8084``

## `Validate Barcode`

Example: ``AB473124829GB``

| Key          | Information |
|:-------------|:-----------:|
| Request Type |    POST     |
| Request URL  | `/validate` |
| Header       |   barcode   |
| Body Type    |             |
