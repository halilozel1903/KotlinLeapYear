package com.example.leapyear.domain.repository

/**
 * Repository abstraction for accessing year data.
 * 
 * This interface resides in the domain layer and defines what the domain needs
 * without knowing how data is retrieved. The data layer provides the implementation.
 * This follows the Dependency Inversion Principle.
 */
fun interface YearRepository {
    /**
     * Retrieves the user's favorite year.
     * 
     * @return Result containing the year value or an error
     */
    fun fetchFavoriteYear(): Result<Int>
}
