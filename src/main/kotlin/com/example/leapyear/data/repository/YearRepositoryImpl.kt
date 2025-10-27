package com.example.leapyear.data.repository

import com.example.leapyear.data.datasource.YearDataSource
import com.example.leapyear.domain.repository.YearRepository

/**
 * Bridges the domain repository abstraction with the concrete data source.
 */
class YearRepositoryImpl(
    private val yearDataSource: YearDataSource
) : YearRepository {

    override fun fetchFavoriteYear(): Result<Int> = yearDataSource.readYear()
}
