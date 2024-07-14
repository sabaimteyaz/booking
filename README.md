# Train Ticket Booking API

This project provides a RESTful API for booking train tickets for a journey from London to France. The train ticket costs $20, and the application includes functionalities for submitting a ticket purchase, viewing receipt details, managing user seats, and more. The data is stored in memory for the duration of the application session.

## Features

1. **Purchase Ticket**: Submit a purchase request for a train ticket.
2. **View Receipt**: Get details of the receipt for the user.
3. **View Users and Seats**: View the users and the seats they are allocated by the requested section (A or B).
4. **Remove User**: Remove a user from the train.
5. **Modify User Seat**: Modify a user's seat.

## Technologies Used

- Java
- Spring Boot
- RESTful API
- In-memory data storage


## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Installation

1. Clone the repository
   ```sh
   git clone https://github.com/sabaimteyaz/booking.git
   cd booking
   mvn clean install
   mvn spring-boot:run
```
