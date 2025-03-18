# Ether Tree 🌳

Android application for working with hierarchical tree structure with Ethereum-like address generation.

## 🌟 Features

- Tree data structure with unlimited nesting
- Recursive navigation between nodes
- Operations on any level:
  - Create new nodes
  - Delete nodes (with cascade deletion)
  - Automatic hierarchy updates
- Unique name generation using SHA-256 hash (last 20 bytes)

## 🛠 Tech Stack

Core components:
- Jetpack Compose - UI framework
- Room - Local data persistence
- Dagger Hilt - Dependency injection
- Coroutines - Asynchronous operations

Additional components:
- Material Design 3 components
- SHA-256 cryptographic hashing
- Clean Architecture approach
