# Clean Architecture in Kotlin Leap Year

This document provides a detailed explanation of the Clean Architecture implementation in this project.

## Table of Contents
- [Overview](#overview)
- [Architecture Layers](#architecture-layers)
- [Dependency Flow](#dependency-flow)
- [Key Design Principles](#key-design-principles)
- [Testing Strategy](#testing-strategy)

## Overview

Clean Architecture is a software design philosophy that separates concerns by organizing code into layers. Each layer has a specific responsibility and dependencies point inward toward the business logic.

### Benefits
- **Testability**: Each layer can be tested independently
- **Flexibility**: Easy to change implementations without affecting other layers
- **Maintainability**: Clear separation of concerns makes code easier to understand
- **Scalability**: Easy to add new features following established patterns

## Architecture Layers

### 1. Presentation Layer (`presentation/`)

The outermost layer responsible for user interaction and displaying information.

**Components:**
- `ConsoleView`: Renders output and handles user input
- `LeapYearViewModel`: Manages presentation logic and state
- `LeapYearUiState`: Sealed class representing possible UI states

**Responsibilities:**
- Display information to the user
- Handle user input
- Transform domain data into displayable format
- Manage UI state

**Dependencies:** Domain layer (Use Cases, Repository interfaces)

### 2. Domain Layer (`domain/`)

The core business logic layer that is independent of external concerns.

**Components:**
- `Year` (Entity/Model): Core business entity with validation
- `IsLeapYearUseCase`: Contains leap year business logic
- `YearRepository` (Interface): Defines data access contract

**Responsibilities:**
- Define business rules and logic
- Validate business entities
- Define interfaces for data access
- No dependencies on frameworks or external systems

**Dependencies:** None (completely independent)

### 3. Data Layer (`data/`)

The outermost layer responsible for data access and external systems.

**Components:**
- `YearRepositoryImpl`: Concrete implementation of repository
- `YearDataSource` (Interface): Defines data source contract
- `ConsoleYearDataSource`: Reads year from console input

**Responsibilities:**
- Implement repository interfaces defined in domain
- Access external data sources (console, API, database, etc.)
- Convert external data to domain models

**Dependencies:** Domain layer (Repository interfaces)

### 4. Dependency Injection (`di/`)

Manages object creation and wiring of dependencies.

**Components:**
- `DependencyContainer`: Simple DI container using lazy initialization

**Responsibilities:**
- Create and provide instances of all layers
- Wire dependencies between layers
- Ensure proper dependency direction

## Dependency Flow

```
┌─────────────────────────────────────────────┐
│         Presentation Layer                  │
│         (depends on Domain)                 │
└─────────────┬───────────────────────────────┘
              │
              ↓
┌─────────────────────────────────────────────┐
│            Domain Layer                     │
│         (no dependencies)                   │
└─────────────┬───────────────────────────────┘
              ↑
              │
┌─────────────┴───────────────────────────────┐
│             Data Layer                      │
│      (depends on Domain interfaces)         │
└─────────────────────────────────────────────┘
```

**Key Rule**: Dependencies always point inward. Inner layers (Domain) never know about outer layers.

## Key Design Principles

### 1. Dependency Inversion Principle
- High-level modules (Presentation) depend on abstractions (Repository interface)
- Low-level modules (Data) also depend on abstractions
- Both depend on interfaces defined in the Domain layer

Example:
```kotlin
// Domain layer defines the interface
interface YearRepository {
    fun fetchFavoriteYear(): Result<Int>
}

// Data layer implements it
class YearRepositoryImpl(dataSource: YearDataSource) : YearRepository

// Presentation depends on the interface, not the implementation
class LeapYearViewModel(repository: YearRepository, useCase: IsLeapYearUseCase)
```

### 2. Single Responsibility Principle
Each class has one reason to change:
- `Year`: Encapsulates year validation and leap year logic
- `IsLeapYearUseCase`: Determines if a year is a leap year
- `YearRepository`: Provides access to year data
- `ConsoleView`: Renders UI

### 3. Open/Closed Principle
Classes are open for extension, closed for modification:
- New data sources can be added by implementing `YearDataSource`
- New views can be added without modifying the ViewModel
- New use cases can be added without modifying existing code

### 4. Interface Segregation Principle
Clients depend only on methods they use:
- `YearDataSource` has only one method: `readYear()`
- `YearRepository` has only one method: `fetchFavoriteYear()`
- Functional interfaces (`fun interface`) enforce this naturally

## Testing Strategy

### Unit Tests
Each layer can be tested in isolation:

**Domain Layer:**
```kotlin
// Test the Year entity
val year = Year(2024)
assert(year.isLeapYear())

// Test the use case
val useCase = IsLeapYearUseCase()
val result = useCase(2024)
assert(result.isSuccess && result.getOrNull() == true)
```

**Presentation Layer:**
```kotlin
// Mock the repository
val mockRepo = object : YearRepository {
    override fun fetchFavoriteYear() = Result.success(2024)
}
val viewModel = LeapYearViewModel(mockRepo, IsLeapYearUseCase())
val state = viewModel.evaluateFavoriteYear()
assert(state is LeapYearUiState.Success)
```

**Data Layer:**
```kotlin
// Mock the data source
val mockDataSource = object : YearDataSource {
    override fun readYear() = Result.success(2024)
}
val repository = YearRepositoryImpl(mockDataSource)
val result = repository.fetchFavoriteYear()
assert(result.isSuccess)
```

### Integration Tests
Test the interaction between layers using real implementations where appropriate.

### End-to-End Tests
Test the complete flow through all layers.

## Example Flow

Let's trace a complete user interaction:

1. **User Input**: User enters "2024"
   ```
   ConsoleView.render() → calls viewModel.evaluateFavoriteYear()
   ```

2. **Presentation Layer**: ViewModel coordinates the flow
   ```
   LeapYearViewModel.evaluateFavoriteYear() → calls repository.fetchFavoriteYear()
   ```

3. **Data Layer**: Repository fetches from data source
   ```
   YearRepositoryImpl.fetchFavoriteYear() → calls dataSource.readYear()
   ConsoleYearDataSource.readYear() → returns Result.success(2024)
   ```

4. **Domain Layer**: Use case applies business logic
   ```
   IsLeapYearUseCase(2024) → creates Year(2024)
   Year(2024).isLeapYear() → returns true
   ```

5. **Back to Presentation**: ViewModel creates UI state
   ```
   Returns LeapYearUiState.Success(2024, true)
   ```

6. **View Renders**: ConsoleView displays the result
   ```
   Output: "2024 is a leap year."
   ```

## Adding New Features

### Adding a New Data Source (e.g., File-based)

1. Implement `YearDataSource` interface:
```kotlin
class FileYearDataSource(private val filePath: String) : YearDataSource {
    override fun readYear(): Result<Int> {
        // Read from file
    }
}
```

2. Update `DependencyContainer`:
```kotlin
private val yearDataSource: YearDataSource by lazy {
    FileYearDataSource("years.txt") // Instead of ConsoleYearDataSource()
}
```

No changes needed in Domain or Presentation layers!

### Adding a New Use Case

1. Create in domain layer:
```kotlin
class GetYearRangeUseCase {
    operator fun invoke(start: Int, end: Int): List<Year> {
        return (start..end).map { Year(it) }
    }
}
```

2. Add to DI container and inject where needed.

## Conclusion

This Clean Architecture implementation demonstrates:
- Clear separation of concerns
- Testable, maintainable code
- Easy to extend with new features
- Independent of frameworks and external systems
- Follows SOLID principles

The architecture may seem over-engineered for a simple leap year checker, but it showcases patterns that scale well to larger applications.
