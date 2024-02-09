# Train Ticket Booking System

This Spring Boot application simulates a train ticket booking system. Users can purchase tickets, view ticket details, retrieve user information by section, remove a user from the train, and modify a user's seat.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Git

### Clone the Repository

```bash
git clone https://github.com/your-username/train-ticket-booking.git
cd train-ticket-booking
Build and Run

```bash
mvn spring-boot:run
The application will be accessible at http://localhost:8080.

###API Endpoints
Book Ticket:
Endpoint: POST /api/ticket/{userId}
Parameters: fromLoc, toLoc, price, section, seatNumber, userId
e.g. JSON Body
{
    "toLoc":"France",
    "fromLoc":"London ",
    "price":20.0,
    "seatNumber":23,
    "section":"A"
}

Get Ticket Details:
Endpoint: GET /api/ticket/{ticketId}
Parameters: ticketId

Get Tickets by Section:
Endpoint: GET /api/ticket
Parameters: section


Remove Ticket:
Endpoint: DELETE /api/ticket/{ticketId}
Parameters: ticketId


Modify Ticket Seat:
Endpoint: PUT /api/ticket?ticketId={...}&newSection={...}&newSeatNumber{...}
Parameters: ticketId, newSection, newSeatNumber


###Running Tests
```bash
mvn test


###Contributing
Fork the repository
Create a feature branch (git checkout -b feature/new-feature)
Commit your changes (git commit -m 'Add new feature')
Push to the branch (git push origin feature/new-feature)
Open a pull request

###License
This README provides basic information on how to get started, run the application, use the API endpoints, run tests, and contribute to the project. Customize it further based on the specific details and features of your application.
