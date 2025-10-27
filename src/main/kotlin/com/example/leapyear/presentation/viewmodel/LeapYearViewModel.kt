package com.example.leapyear.presentation.viewmodel

import com.example.leapyear.domain.repository.YearRepository
import com.example.leapyear.domain.usecase.IsLeapYearUseCase
import com.example.leapyear.presentation.state.LeapYearUiState

/**
 * Coordinates the leap year flow between the UI and domain layers.
 */
class LeapYearViewModel(
    private val yearRepository: YearRepository,
    private val isLeapYear: IsLeapYearUseCase
) {

    fun evaluateFavoriteYear(): LeapYearUiState {
        return yearRepository.fetchFavoriteYear()
            .fold(
                onSuccess = { year ->
                    val isLeapYearResult = isLeapYear(year)
                    LeapYearUiState.Success(year, isLeapYearResult)
                },
                onFailure = { throwable ->
                    LeapYearUiState.Error(throwable.message ?: "Unknown error")
                }
            )
    }
}
