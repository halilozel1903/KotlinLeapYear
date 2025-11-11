package com.example.leapyear.domain.usecase

import com.example.leapyear.domain.model.Year

/**
 * Use case that determines whether a year qualifies as a leap year.
 * 
 * This use case follows the Single Responsibility Principle by focusing
 * solely on the leap year determination logic.
 */
class IsLeapYearUseCase {
    /**
     * Executes the use case to check if a year is a leap year.
     * 
     * @param year The year value to check
     * @return Result containing true if leap year, false otherwise, or an error
     */
    operator fun invoke(year: Int): Result<Boolean> {
        return try {
            val yearEntity = Year(year)
            Result.success(yearEntity.isLeapYear())
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }
}
