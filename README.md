# Kotlin Leap Year 📆 💀 👀

![Kotlin Leap Year](https://whizpool.com/wp-content/uploads/2022/10/kotlin1.jpg)

A **Leap Year** is a year that is evenly divisible by 4, except for years that are divisible by 100 but not by 400. This means that the year 2000 was a leap year, but 1900 was not.

This project demonstrates **Clean Architecture** principles in Kotlin, showcasing how to structure a simple application with proper separation of concerns.

## 🏗️ Clean Architecture

This project follows Clean Architecture principles with clear separation of layers:

### Architecture Layers

```
┌─────────────────────────────────────────────┐
│         Presentation Layer                  │
│  ┌──────────┐  ┌───────────┐  ┌─────────┐ │
│  │   View   │  │ ViewModel │  │ UIState │ │
│  └──────────┘  └───────────┘  └─────────┘ │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│            Domain Layer                     │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐ │
│  │ Use Case │  │ Entity   │  │Repository│ │
│  │          │  │ (Model)  │  │Interface │ │
│  └──────────┘  └──────────┘  └──────────┘ │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│             Data Layer                      │
│  ┌──────────────┐  ┌──────────────────────┐│
│  │ Repository   │  │    Data Source       ││
│  │Implementation│  │                      ││
│  └──────────────┘  └──────────────────────┘│
└─────────────────────────────────────────────┘
```

### Layer Responsibilities

#### 📱 Presentation Layer (`presentation/`)
- **View**: Renders UI and handles user interactions (console-based)
- **ViewModel**: Manages presentation logic and transforms domain data to UI state
- **UIState**: Represents possible UI states (Success, Error)

#### 💼 Domain Layer (`domain/`)
- **Use Cases**: Contains business logic (e.g., `IsLeapYearUseCase`)
- **Entities/Models**: Core business models with validation (`Year`)
- **Repository Interfaces**: Abstractions for data access

#### 💾 Data Layer (`data/`)
- **Repository Implementation**: Concrete implementation of domain repositories
- **Data Sources**: Handle data retrieval (console input, API, database, etc.)

#### 🔧 Dependency Injection (`di/`)
- **DependencyContainer**: Manages object creation and dependency injection

### Key Principles

1. **Dependency Rule**: Dependencies point inward. Inner layers know nothing about outer layers.
2. **Single Responsibility**: Each class has one reason to change.
3. **Dependency Inversion**: High-level modules don't depend on low-level modules; both depend on abstractions.
4. **Interface Segregation**: Use focused interfaces (`YearDataSource`, `YearRepository`).

## 🚀 Usage

### Running the Application

```bash
# Compile the project
kotlinc -d out $(find src/main/kotlin -name "*.kt" -type f)

# Run the application
kotlin -classpath out com.example.leapyear.MainKt
```

### Example Interaction

```kotlin
Enter favorite year: 2024
2024 is a leap year.
```

```kotlin
Enter favorite year: 2023
2023 is not a leap year.
```

### Input Validation

The application validates input and provides meaningful error messages:

```kotlin
Enter favorite year: abc
Error: Invalid input
```

```kotlin
Enter favorite year: -5
Error: Year must be a positive number
```

## 📝 Code Example

The core leap year logic is encapsulated in the domain entity:

```kotlin
fun isLeapYear(year: Int?): Boolean {
    return if (year != null) {
        (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)
    } else {
        false
    }
}

fun main() {
    print("Enter favorite year: ")
    val year = readlnOrNull()?.toInt()
    if (isLeapYear(year)) {
        println("$year is a leap year.")
    } else {
        println("$year is not a leap year.")
    }
}
```

In this program, the `isLeapYear()` function takes a year as input and returns true if it is a leap year, and false otherwise. The function checks if the year is divisible by 4, and if it is, it checks if it is not divisible by 100 or if it is divisible by 400.

## 🧪 Testing

The architecture makes testing easier by allowing each layer to be tested independently:

- **Unit Tests**: Test use cases and domain logic in isolation
- **Integration Tests**: Test repository implementations with mock data sources
- **UI Tests**: Test view models with mock repositories

## Donation 💸

You can support by buying a coffee. ☕️

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/halilozel1903)


## License ℹ️
```
MIT License

Copyright (c) 2024 Halil OZEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```