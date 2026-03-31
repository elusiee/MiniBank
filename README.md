# MiniBank

MiniBank is a simple Java console-based banking application that simulates basic ATM and banking operations. It allows a user to authenticate, navigate a menu, and perform common account actions in a straightforward command-line interface.

## Features

- User authentication
- ATM-style menu navigation
- View account details
- Deposit money
- Withdraw money
- Basic console interaction using Java
- Simple project structure for learning and practice

## Purpose of the Project

This project was built to practice core Java programming concepts, including:

- Classes and objects
- Methods
- Control flow
- User input with `Scanner`
- Conditional statements
- Loops
- Basic application structure

MiniBank is a beginner-friendly project that demonstrates how a real-world banking workflow can be modeled in Java.

## Technologies Used

- Java
- IntelliJ IDEA
- Command-line / terminal interface

## Project Structure

A typical structure for the project may look like this:

```text
MiniBank/
├── src/
│   ├── Main.java
│   ├── User.java
│   ├── Account.java
│   └── other project files...
└── README.md

How It Works

When the application starts, the user is prompted to log in or authenticate. After successful authentication, the program displays an ATM menu with different banking options such as checking balance, depositing funds, withdrawing funds, or exiting the application.

The application runs in a loop until the user chooses to quit.

Requirements

Before running the project, make sure you have:

1. Java JDK 8 or later installed
2. IntelliJ IDEA or another Java IDE
3. Git installed if you want to clone the repository
How to Run the Project
Option 1: Run in IntelliJ IDEA
1. Open IntelliJ IDEA
2. Select Open
3. Choose the MiniBank project folder
4. Wait for the project to load
5. Open Main.java
6. Click the Run button
Option 2: Run from the Terminal
1. Open a terminal in the project folder
2. Compile the Java files:

javac src/*.java
Run the program:
java -cp src Main
How to Clone the Project
git clone https://github.com/elusiee/MiniBank.git
cd MiniBank
Example Usage
Welcome to MiniBank
Enter username:
Enter password:

Login successful

1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Learning Goals

This project helped strengthen understanding of:

Structuring a Java console application
Handling user input safely
Building reusable methods
Simulating simple banking logic
Managing a project with Git and GitHub
Future Improvements

Possible upgrades for the project include:

Account registration
Multiple user accounts
Data persistence using files or a database
PIN encryption / improved authentication
Transaction history
GUI version using JavaFX or Swing
Exception handling improvements
Author

Elusiee

GitHub: https://github.com/elusiee

License

This project is for learning and educational purposes.
