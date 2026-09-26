# WolfMedia Database System

A command-line media platform developed for CSC 540, Database Management Concepts, at North Carolina State University.

WolfMedia models the data and business operations behind a streaming service for songs, albums, artists, podcasts, subscribers, sponsors, and royalty payments.

## Project scope

The Java application connects to MariaDB through JDBC and provides operations for:

- Account and user management
- Artists, record labels, albums, and songs
- Podcast hosts, podcasts, and episodes
- Genres, collaborations, releases, and subscriptions
- Sponsor relationships and advertisements
- Play-count reporting
- Royalty and host payments
- Subscriber revenue reporting

The implementation uses prepared statements for the main application operations and formats query results for an interactive terminal interface.

## Repository structure

```text
Team_W/
  WolfMedia.java          Main command-line application
  mariadb-java-client.jar JDBC driver used by the project
HW_sol/                   Individual database exercises
TeamW_ProjectReport*.pdf  Design, implementation, and final reports
```

## Technology

- Java
- MariaDB
- JDBC
- SQL

## Running locally

The checked-in configuration points to the original course database and will not run without access to that environment. To adapt the project:

1. Create a MariaDB database using the schema described in the project reports.
2. Update the JDBC URL in `Team_W/WolfMedia.java`.
3. Provide database credentials through environment variables or a local configuration file that is excluded from Git.
4. Add a compatible MariaDB JDBC driver to the classpath.
5. Compile and run the application.

Example:

```bash
cd Team_W
javac -cp mariadb-java-client-3.1.2.jar WolfMedia.java
java -cp ".:mariadb-java-client-3.1.2.jar" WolfMedia
```

On Windows, replace `:` in the classpath with `;`.

## What I learned

- How to translate requirements into relational entities and relationships
- How to implement CRUD workflows with JDBC and prepared statements
- How transactions protect multi-step updates
- How reporting queries aggregate operational data
- How schema design affects application code and query complexity

## Status

This is an archived course project. The next improvement would be to extract configuration from source code, add a reproducible schema and seed script, and introduce integration tests.

## Academic context

Current students should follow their institution's academic integrity rules and should not submit this work as their own.

