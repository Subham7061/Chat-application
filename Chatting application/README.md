<h3>Chat Application using Java Swing and Socket Programming</h3> 

<p>This is a simple chat application developed in Java using the Swing library for the graphical user interface (GUI) and socket programming for communication between clients and a server. This README will provide you with instructions on how to set up and run the chat application.</p>

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)
- [Project Structure](#project-structure)
  

## Features

- Real-time chat functionality.
- Users can send and receive messages.
- User-friendly graphical user interface (GUI) built using Java Swing.
- Server and client code are well-structured and modular.

## Requirements

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 8 or higher installed on your system.
- A basic understanding of Java and socket programming.
- IDE of your choice (e.g., Eclipse, IntelliJ IDEA) for code editing and running.

## Usage

1. Clone the repository to your local machine:

   ```
   git clone https://github.com/yourusername/chat-application.git
   ```

2. Open your IDE and import the project.

3. Open the `App.java`(Which is a server file) file in your IDE and run the server. By default, it will listen on port 12345. You can change the port in the code if needed.

4. Open the `Client.java` file in your IDE and run the client. You can run multiple instances of the client to simulate multiple users.

5. In the client GUI, enter your username and the server's IP address and port number. 

6. Start chatting! You can send messages by typing in the text field at the bottom and pressing "Enter" or clicking the "Send" button.

7. Messages sent by one client will be received by the connected clients through the server.

8. To disconnect from the server, simply close the client application.

## Project Structure

The project structure is organized as follows:

```
chat-application/
│
├── src/
│   ├──Image folder      #contains images used in GUI
│   ├── App.java        # Server-side code
│   └── Client.java        # Client-side code

│
├── README.md              # Project documentation
└── ...
```

