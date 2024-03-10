fun isLeapYear(year: Int?) = (year != null) && (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)

fun main() {
    print("Enter favorite year: ")
    val year = readlnOrNull()?.toInt()
    println("${year ?: "Invalid input"} is ${if (isLeapYear(year)) "a leap" else "not a leap"} year.")
}