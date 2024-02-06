# Train-Ticket

## Description
The Train Ticket project is a Spring Boot application for managing train tickets, including purchasing, modifying seats, and viewing ticket details.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)

## Features
- Purchase train tickets from London to France.
- Modify a user's seat within available sections.
- View ticket details and user allocations.
- Remove a user from the train.
- Automatic seat assignment if not provided during purchase.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 
- Apache Maven
- Git
- Eclipse Used

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/suriya098/Train-Ticket
2. Navigate to the project directory:
   cd trainticket
3.Build the project::
   mvn clean install

### Usage
   mvn spring-boot:run
   Access the application : http://localhost:8080.

###Endpoints
Purchase Ticket: POST /api/tickets/purchase
Modify User's Seat: PUT /api/tickets/modifySeat/{userId}
View Ticket Details: GET /api/tickets/{userId}
View Users and Seats by Section: GET /api/tickets/section/{section}
Remove User from Train: DELETE /api/tickets/remove/{userId}
