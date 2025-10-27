package com.example.leapyear.domain.repository

/**
 * Repository abstraction that supplies the user's preferred year.
 */
fun interface YearRepository {
    fun fetchFavoriteYear(): Result<Int>
}
