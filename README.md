# Library Management System with JPA and PostgreSQL Integration

This project is a library management system developed in Java using persistence based on JPA (Java Persistence API) and integration with the PostgreSQL database. The system allows management of a library catalog and its loans, providing functionalities to add, remove, and search for books and magazines, as well as to track loans.

## Key Features

- **Catalog Management**: Addition and removal of books and magazines from the catalog.
- **Advanced Search**: Search by ISBN, publication year, author, and title.
- **Loan Management**: User registration, tracking borrowed items, and start and end loan dates.
- **Loan Tracking**: View currently borrowed items and overdue loans.

## Key Components of the Project

The project includes:

- **Catalog Classes**: Classes to represent books and magazines, with distinct attributes for each type of item.
- **Data Access Object (DAO) Classes**: Classes for data access and management, including CRUD operations and queries.
- **Integration with PostgreSQL**: Configuration and integration with the PostgreSQL database for persistence.

## Usage

1. **Setup PostgreSQL**: Ensure PostgreSQL is installed and running on your local machine. If not installed, download and install it from [PostgreSQL Downloads](https://www.postgresql.org/download/).
2. **Database Configuration**: Open the `persistence.xml` file and modify the following properties according to your PostgreSQL configuration:

   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/LibraSysDB"/>
   <property name="javax.persistence.jdbc.user" value="postgres"/>
   <property name="javax.persistence.jdbc.password" value="12345"/>

- Replace **localhost** with your PostgreSQL server hostname if it's not running locally.
- Modify the port number **5432** if your PostgreSQL server is running on a different port.
- Replace **LibraSysDB** with the name of your database.
- Update the **javax.persistence.jdbc.user** and **javax.persistence.jdbc.password** properties with your PostgreSQL username and password.

3. **Run the Application**: Compile and run the Java application to start the library management system. Ensure that the PostgreSQL server is running before running the application.
4. **Interact with the System**: Once the application is running, you can interact with it using the provided functionalities to manage the catalog and loans.

## Dependencies

- Java 8 or higher
- PostgreSQL database
- Maven (for dependency management and building)
