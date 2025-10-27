package com.example.leapyear.domain.usecase

/**
 * Determines whether a year qualifies as a leap year.
 */
class IsLeapYearUseCase {
    operator fun invoke(year: Int): Boolean =
        (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)
}
