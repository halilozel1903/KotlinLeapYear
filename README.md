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

```kotlin
Enter favorite year: 2023
2023 is not a leap year.
```
## Donation 💸

If this project help 💁 you, Can you give me a cup of coffee? ☕

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/halilozel1903)


## License ℹ️
```
MIT License

Copyright (c) 2023 Halil OZEL

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