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
