# 🥗 NutriMaster - Premium E-Commerce Experience
> **High-performance supplement e-commerce built with Kotlin Multiplatform (KMP).**

![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin)
![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4?style=for-the-badge&logo=jetpackcompose)
![Status](https://img.shields.io/badge/Status-In--Development-orange?style=for-the-badge)

---

## 📖 Project Overview
**NutriMaster** is a cross-platform mobile application designed to deliver a seamless shopping experience for health and fitness enthusiasts. By using **Compose Multiplatform**, this project shares 100% of its business logic and UI components between **Android** and **iOS**, ensuring feature parity and high performance.

---

## 🎨 UI/UX Design System (Figma)
The project is built upon a high-fidelity design system. Below are the core interface modules being implemented:

<details>
<summary>📸 Click to expand the Design Gallery</summary>

| Module: Profile & Auth | Module: Storefront |
| :---: | :---: |
| <img src="https://github.com/user-attachments/assets/1aaebc16-0f14-4f43-a822-984b5ce781e6" width="200" /> | <img src="https://github.com/user-attachments/assets/c0298e01-38d8-4c32-8f9d-33d2848a9858" width="200" /> |
| <img src="https://github.com/user-attachments/assets/5d0a2f0a-5fe1-4734-a169-996adc028d9d" width="200" /> | <img src="https://github.com/user-attachments/assets/df586c72-c839-4b85-afb3-edbc2dedbc35" width="200" /> |

| Module: Checkout | Module: Navigation |
| :---: | :---: |
| <img src="https://github.com/user-attachments/assets/d5b2fa49-4d23-4953-8e47-3122295f0936" width="200" /> | <img src="https://github.com/user-attachments/assets/4246bbe8-7669-4d4a-a14f-a368f3602931" width="200" /> |
| <img src="https://github.com/user-attachments/assets/f35a922d-6874-483e-8acc-685d3cd3f25c" width="200" /> | <img src="https://github.com/user-attachments/assets/0b342994-9bed-4b2b-892b-a7f37f0b9ef9" width="200" /> |

</details>

---

## 🏗️ Architecture & Project Structure
This project follows **Clean Architecture** and **MVVM** patterns to decouple business rules from platform-specific code.



* **[`/composeApp`](./composeApp/src)**: The shared core.
    * `commonMain`: Unified logic and UI components (100% shared).
    * `androidMain` / `iosMain`: Platform-specific APIs and interop.
* **[`/iosApp`](./iosApp/iosApp)**: Native entry point and SwiftUI wrapper for the iOS target.

---

## 🛠️ Key Technical Implementations
* **Repository Pattern**: Abstracted data layer for seamless integration with Ktor and local persistence.
* **Immutable State**: Leveraging Kotlin **Data Classes** for thread-safe UI updates.
* **Component-Driven UI**: Reusable atomic components like `ProfileForm` and custom Input fields.

---

## 🚀 Getting Started

### Build Android
```shell
# macOS/Linux
./gradlew :composeApp:assembleDebug
# Windows
.\gradlew.bat :composeApp:assembleDebug

Build iOS
Open the /iosApp directory in Xcode and run the project using your preferred simulator.
```

👤 About the Developer
Gabriel Mobile Developer | Freelancer | Tech Enthusiast 📍 Goiânia, Brazil

---

## 🎓 Education & Academic Journey
**Unisinos** | *Bachelor in Information Systems* **Expected Graduation:** July 2026  
**Academic Standing:** GPA 3.17 / 4.0

### 📚 Current & Pending Coursework (2026 Focus)
To complete my degree, I am deep-diving into high-level systems engineering and optimization:

#### **2026/1 - Systems Core**
* **Final Graduation Project I**: Starting the research and architecture for my Capstone.
* **Advanced Algorithms**: Graphs, Hashing, and Heaps (Essential for high-performance Mobile apps).
* **Modeling & Optimization**: Production systems efficiency.

#### **2026/2 - Specialized Engineering**
* **Internet of Things (IoT)**: Sensors, protocols, and real-world applications.
* **Competitive Intelligence**: Using tech for business strategic advantages.
* **Final Graduation Project II**: Completion of my Capstone Project.

---

Inspired by modern e-commerce trends and powered by Kotlin Multiplatform.
