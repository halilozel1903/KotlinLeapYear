package com.example.leapyear.domain.usecase

import com.example.leapyear.domain.usecase.IsLeapYearUseCase

/**
 * Tests for the IsLeapYearUseCase.
 */
fun main() {
    val useCase = IsLeapYearUseCase()
    
    testLeapYears(useCase)
    testNonLeapYears(useCase)
    testEdgeCases(useCase)
    testInvalidInput(useCase)
    
    println("✓ All IsLeapYearUseCase tests passed!")
}

private fun testLeapYears(useCase: IsLeapYearUseCase) {
    // Test various leap years
    val leapYears = listOf(2024, 2020, 2016, 2000, 1600)
    leapYears.forEach { year ->
        val result = useCase(year)
        assert(result.isSuccess) { "Should succeed for year $year" }
        assert(result.getOrNull() == true) { "$year should be a leap year" }
    }
}

private fun testNonLeapYears(useCase: IsLeapYearUseCase) {
    // Test various non-leap years
    val nonLeapYears = listOf(2023, 2021, 2019, 1900, 1700)
    nonLeapYears.forEach { year ->
        val result = useCase(year)
        assert(result.isSuccess) { "Should succeed for year $year" }
        assert(result.getOrNull() == false) { "$year should not be a leap year" }
    }
}

private fun testEdgeCases(useCase: IsLeapYearUseCase) {
    // Test edge cases
    val result1 = useCase(1)
    assert(result1.isSuccess && result1.getOrNull() == false) { "Year 1 should not be a leap year" }
    
    val result4 = useCase(4)
    assert(result4.isSuccess && result4.getOrNull() == true) { "Year 4 should be a leap year" }
}

private fun testInvalidInput(useCase: IsLeapYearUseCase) {
    // Test invalid input
    val result0 = useCase(0)
    assert(result0.isFailure) { "Year 0 should fail validation" }
    
    val resultNegative = useCase(-5)
    assert(resultNegative.isFailure) { "Negative year should fail validation" }
}
