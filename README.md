# Java TCP Networking with Cryptography Project

## Overview

This project is designed to demonstrate the implementation of a client/server model using Java TCP sockets, incorporating cryptography for secure communication. It focuses on Java TCP networking, cryptography, Java Object Serialization/Deserialization, lightweight remote method invocation, threading, and connecting to a relational database. The server handles multiple client connections concurrently, authenticating users against a database and performing tasks requested by clients.

## Objectives

- To implement a client/server model using Java TCP sockets.
- To use cryptography for secure user authentication.
- To serialize/deserialize Java objects for task execution.
- To support concurrent client connections using threading.
- To interact with a relational database for user authentication.

## Requirements

- Java Development Kit (JDK)
- Integrated Development Environment (IDE) like NetBeans or Eclipse
- Java Derby or MySQL Server for the database
- Basic understanding of TCP/IP networking, Java programming, and cryptography

## Implementation Details

### Server

- Generates a key pair upon startup and uses the private key for decrypting messages.
- Authenticates users against credentials stored in a relational database.
- Executes mathematical tasks requested by the client, such as Fibonacci calculation, GCD, and Factorial, and returns the results.

### Client

- Connects to the server and receives the public key for encrypting messages.
- Encrypts the username and password before sending to the server for authentication.
- After authentication, interacts with a menu to submit tasks for server-side computation.

### Cryptography

- Utilizes RSA algorithm for key pair generation and message encryption/decryption.
- Ensures secure communication between client and server by encrypting sensitive information.

### Database Connection

- Manages connections to the relational database for querying user credentials.
- Validates user login details to authenticate users before allowing task submissions.

## How to Run

1. Start the database server and create the necessary schema and tables as per the provided SQL scripts.
2. Compile and run the `ComputeServer` application. It will wait for client connections and handle requests.
3. Compile and run one or more instances of the `ComputeClient` application to connect to the server and perform operations.

## Testing

- Demonstrate the server's ability to handle multiple concurrent client connections.
- Show encryption of messages for secure transmission of sensitive information like usernames and passwords.
- Verify the correct execution and result retrieval of tasks submitted by the client.

## Report

Include screenshots of the program outputs for all tests with annotations to demonstrate the successful implementation of the project components. Tests should cover the concurrent handling of client connections, task execution, and secure communication.

