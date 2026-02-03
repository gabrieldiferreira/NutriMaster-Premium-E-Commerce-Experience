# 📱 NutriMaster - Premium E-Commerce Experience
> **Feature Branch:** `feature-form`  
> *Translating high-fidelity Figma designs into scalable Kotlin Multiplatform architecture.*

![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4?style=for-the-badge&logo=jetpackcompose)
![Build Status](https://img.shields.io/badge/Build-Success-brightgreen?style=for-the-badge)

---

## 📖 Overview
This branch implements the user profile module for the **NutriMaster** project. By leveraging **Kotlin Multiplatform (KMP)**, I have unified the business logic and data structures for both Android and iOS targets. The primary focus was transforming a high-fidelity Figma design into a functional, clean codebase.

## 🖼️ Design vs. Implementation
A core part of this feature was ensuring 1:1 fidelity between the design system and the final implementation.

<p align="center">
  <img width="700" alt="collage_project" src="https://github.com/user-attachments/assets/782e14d4-1954-4bd8-a7b4-6e285bf411de" />
</p>

---

## 🏗️ Architecture: The "Responsibility Ladder"
I've structured the code following **Clean Architecture** principles to ensure the project remains testable and scalable.

### 🔹 Data & Domain Layer
* **`Customer.kt` (Data Class)**: Pure data model representing the user. It utilizes Kotlin's immutability for safe state handling and provides the `copy()` method for efficient updates.
* **`CustomerRepository.kt`**: The domain contract (interface) that defines how profile data is fetched and managed.
* **`CustomerRepositoryImpl.kt`**: The concrete implementation of the repository, currently prepared for future API (Ktor) or local database (Room) integrations.

### 🔹 UI Layer (Compose Multiplatform)
* **`ProfileScreen`**: The high-level orchestrator responsible for layout structure, navigation, and observing UI state.
* **`ProfileForm`**: A decoupled, atomic UI component designed strictly for capturing user inputs (First Name, Last Name, Email, etc.).

---

## 📂 Project Structure
As a **Kotlin Multiplatform** project, the code is organized to maximize sharing across platforms:

| Directory | Platform | Purpose |
| :--- | :--- | :--- |
| [`/commonMain`](./composeApp/src/commonMain/kotlin) | **Shared


Build iOS
Open the /iosApp directory in Xcode and run the project or use the IDE run configurations.

📈 Roadmap for this Feature
[ ] State Management: Integrate ProfileViewModel for lifecycle-aware state handling.

[ ] Validations: Implement real-time validation for Email, Phone, and Postal Codes.

[ ] Persistence: Connect the repository to a local cache system.

Developed by Gabriel Mobile Developer | Freelancer 📍 Goiânia, Brazil
