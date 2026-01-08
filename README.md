Food Delivery System (Java – CLI + JavaFX GUI)

A Java-based Food Delivery System built as an academic Data Structures & OOP project, featuring both Command Line Interface (CLI) and JavaFX Graphical User Interface (GUI).

This project demonstrates real-world usage of core data structures such as BST, Stack, Queue, Priority Queue (Heap), and Graph, along with JavaFX UI, clean service-layer architecture, and order processing logic.

📌 Project Overview

The Food Delivery System allows users to:

Sign up and log in

View food menu

Place multiple-item orders

Choose Express or Normal delivery

Manage orders using queues and stacks

View delivery routes using graph traversal

Use CLI or GUI interchangeably

🧠 Concepts & Technologies Used
🔹 Programming & UI

Java (OOP)

JavaFX (GUI)

CSS (Dark Theme UI)

CLI-based interaction

🔹 Data Structures
Data Structure	Usage
BST	Store food menu items
Queue (Linked List)	Normal order processing
Priority Queue (Heap)	Express order priority
Stack	Undo last order
Graph	Delivery network routes
ArrayList	Order items


✨ Features
👤 User Management

Signup

Login (required before placing orders)

🍽️ Food Menu

Stored using Binary Search Tree

Displayed in CLI and GUI (TableView)

Supports search and traversal

🛒 Order System

Multiple food items per order

Quantity control (+ / − buttons in GUI)

Express or Normal delivery

Order summary popup with total bill

Estimated delivery time display

📦 Order Processing
Type	Data Structure
Normal Orders	Queue (Linked List)
Express Orders	Priority Queue (Heap)
Undo Order	Stack
🚚 Delivery Network

Implemented using Graph

Supports:

BFS traversal

DFS traversal

Simulates city delivery routes

🎨 GUI Highlights (JavaFX)

Dark theme UI (custom CSS)

TableView for:

Food menu

Selected order items

Buttons:

Place Order

View Menu

Quantity controls

Confirmation dialog before order placement

🖥️ CLI Features

Full menu-based interaction

Place and undo orders

View restaurants

Display delivery graph

BFS & DFS route visualization

▶️ How to Run the Project
🔹 Requirements

Java JDK 17 or higher

JavaFX SDK

VS Code / IntelliJ IDEA

▶️ Run CLI Version

Open terminal in project root

Compile:

javac app/FoodDeliveryCLI.java


Run:

java app.FoodDeliveryCLI

▶️ Run GUI Version (JavaFX)

Make sure JavaFX is properly set up

Run:

java app.FoodDeliveryGUI


Or run directly from IDE using Run ▶️

🎯 Academic Learning Outcomes

✔ Object-Oriented Programming
✔ Data Structures Implementation
✔ JavaFX UI Development
✔ MVC / Service-Based Architecture
✔ Real-world system simulation

🚀 Future Improvements

Database integration (MySQL / SQLite)

Payment gateway simulation

Admin dashboard

Order history view

Real-time delivery tracking

JSON persistence

👨‍💻 Author

Muhammad Bilal
Software Engineering Student
IQRA University

📜 License

This project is for educational purposes only.
