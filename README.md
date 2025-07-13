# QuoteVault

"QuoteVault" is a modern Kotlin Multiplatform (KMP) application designed to deliver daily inspiration right to your fingertips. Built with 100% Compose UI, it targets Android, iOS, and Desktop platforms, providing a consistent and delightful user experience across all devices.

## ✨ Features

* **Multiplatform Support:** Seamlessly runs on Android, iOS, and Desktop.

* **Daily Quotes:** Fetches inspiring quotes from a public API.

* **Clean UI:** Beautiful and intuitive user interface built entirely with Compose Multiplatform.

* **Shimmer Effect:** Provides a smooth loading experience with shimmer animations.

* **Theming:** Dynamic and consistent theming across platforms using MaterialKolor.

## 🏛️ Architecture

This project adheres to a **Clean Architecture** approach, leveraging the **MVVM (Model-View-ViewModel)** pattern and a dedicated **Repository Pattern** to ensure separation of concerns, testability, and maintainability.

* **Model:** Represents the data and business logic.

* **View:** The Compose UI, responsible for displaying data and handling user interactions.

* **ViewModel:** Acts as a bridge between the View and the Model, exposing data streams and handling UI-related logic.

* **Repository:** Abstracts the data sources (e.g., network API, local database), providing a clean API for the ViewModels to retrieve and store data without knowing the underlying implementation details.

## 🚀 Technologies Used

This project leverages the power of Kotlin Multiplatform and Compose Multiplatform, along with a suite of cutting-edge libraries:

* [**Kotlin Multiplatform**](https://kotlinlang.org/docs/multiplatform.html): For sharing code across Android, iOS, and Desktop.

* [**Compose Multiplatform**](https://www.jetbrains.com/lp/compose-multiplatform/): For building declarative UIs across all target platforms.

* [**Koin**](https://insert-koin.io/): A pragmatic lightweight dependency injection framework for Kotlin.

* [**Ktor Client**](https://ktor.io/docs/client.html): A powerful and flexible HTTP client for making network requests to the quote API.

* [**Voyager**](https://github.com/adrielcafe/voyager): A pragmatic navigation library for Compose Multiplatform, simplifying screen transitions.

* [**Shimmer**](https://github.com/valentinilk/compose-shimmer): For implementing elegant shimmer loading effects.

* [**MaterialKolor**](https://github.com/jordond/MaterialKolor): For dynamic and consistent theming based on Material Design principles.

* [**Compose-Sonner**](https://github.com/dokar3/compose-sonner): A highly customizable toast/notification library for Compose Multiplatform.

* [**Free API**](https://freeapi.app/): Used as the public API to fetch inspiring quotes.

## 🛠️ Setup and Run

To get this project up and running on your local machine, follow these steps:

### Prerequisites

* [Java Development Kit (JDK) 17 or higher](https://www.oracle.com/java/technologies/downloads/)

* [Android Studio](https://developer.android.com/studio) (for Android development)

* [Xcode](https://developer.apple.com/xcode/) (for iOS development on macOS)

* [Kotlin Multiplatform Mobile Plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) (for Android Studio)

### Clone the Repository

```
git clone [https://github.com/hemantbeast/QuoteVault-KMP.git](https://github.com/hemantbeast/QuoteVault-KMP.git)
cd QuoteVault
```

### Run on Android

1. Open the project in Android Studio.

2. Select the `android` run configuration.

3. Click the 'Run' button or press `Shift + F10`.

### Run on iOS

1. Open the project in Android Studio.

2. Select the `iosApp` run configuration.

3. Choose your desired iPhone simulator or a connected device.

4. Click the 'Run' button or press `Shift + F10`.
   *Alternatively, you can open `iosApp/iosApp.xcodeproj` in Xcode and run from there.*

### Run on Desktop

1. Open the project in Android Studio or IntelliJ IDEA.

2. Select the `desktop` run configuration.

3. Click the 'Run' button or press `Shift + F10`.

## 🤝 Contributing

Contributions are welcome! If you have any ideas, suggestions, or bug reports, please open an issue or submit a pull request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/hemantbeast/QuoteVault-KMP/blob/main/LICENSE) file for details.

---

<div align="center">
  Made with ❤️ using Kotlin Multiplatform
</div>