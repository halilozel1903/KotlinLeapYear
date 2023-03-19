# Kotlin Leap Year 📆 💀 👀

A **Leap Year** is a year that is evenly divisible by 4, except for years that are divisible by 100 but not by 400. This means that the year 2000 was a leap year, but 1900 was not.

Here is a program in `Kotlin` that determines whether a given year is a leap year or not:

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

In the main function, we can test the `isLeapYear()` function by passing in a year and printing whether it is a leap year or not. In this case, we're checking the year 2023, which is not a leap year.
