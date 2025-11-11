package com.example.leapyear.domain.model

/**
 * Tests for the Year domain entity.
 */
fun main() {
    testValidYear()
    testLeapYearLogic()
    testInvalidYear()
    println("✓ All Year entity tests passed!")
}

private fun testValidYear() {
    // Test valid year creation
    val year = Year(2024)
    assert(year.value == 2024) { "Year value should be 2024" }
}

private fun testLeapYearLogic() {
    // Test leap year logic
    assert(Year(2024).isLeapYear()) { "2024 should be a leap year" }
    assert(!Year(2023).isLeapYear()) { "2023 should not be a leap year" }
    assert(Year(2000).isLeapYear()) { "2000 should be a leap year (divisible by 400)" }
    assert(!Year(1900).isLeapYear()) { "1900 should not be a leap year (divisible by 100 but not 400)" }
    assert(Year(2020).isLeapYear()) { "2020 should be a leap year" }
    assert(!Year(2021).isLeapYear()) { "2021 should not be a leap year" }
}

private fun testInvalidYear() {
    // Test invalid year creation
    try {
        Year(0)
        error("Should have thrown IllegalArgumentException for year 0")
    } catch (e: IllegalArgumentException) {
        // Expected
    }
    
    try {
        Year(-1)
        error("Should have thrown IllegalArgumentException for negative year")
    } catch (e: IllegalArgumentException) {
        // Expected
    }
}
