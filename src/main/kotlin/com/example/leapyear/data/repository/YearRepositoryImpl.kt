package com.example.leapyear.data.repository

import com.example.leapyear.data.datasource.YearDataSource
import com.example.leapyear.domain.repository.YearRepository

/**
 * Concrete implementation of [YearRepository] that bridges the domain and data layers.
 * 
 * This class follows the Repository pattern, abstracting data source details
 * from the domain layer and enabling easy testing with mock data sources.
 */
class YearRepositoryImpl(
    private val yearDataSource: YearDataSource
) : YearRepository {

    /**
     * Fetches the user's favorite year from the configured data source.
     * 
     * @return Result containing the year value or an error
     */
    override fun fetchFavoriteYear(): Result<Int> = yearDataSource.readYear()
}
