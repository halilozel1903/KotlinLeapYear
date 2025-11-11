package com.example.leapyear.presentation.viewmodel

import com.example.leapyear.domain.repository.YearRepository
import com.example.leapyear.domain.usecase.IsLeapYearUseCase
import com.example.leapyear.presentation.state.LeapYearUiState

/**
 * Coordinates the leap year flow between the UI and domain layers.
 * 
 * This ViewModel follows the MVVM pattern and acts as the presentation logic
 * layer, transforming domain results into UI states.
 */
class LeapYearViewModel(
    private val yearRepository: YearRepository,
    private val isLeapYearUseCase: IsLeapYearUseCase
) {

    /**
     * Evaluates if the user's favorite year is a leap year.
     * 
     * @return The UI state representing the result or error
     */
    fun evaluateFavoriteYear(): LeapYearUiState {
        return yearRepository.fetchFavoriteYear()
            .fold(
                onSuccess = { year ->
                    isLeapYearUseCase(year).fold(
                        onSuccess = { isLeap ->
                            LeapYearUiState.Success(year, isLeap)
                        },
                        onFailure = { throwable ->
                            LeapYearUiState.Error(throwable.message ?: "Invalid year")
                        }
                    )
                },
                onFailure = { throwable ->
                    LeapYearUiState.Error(throwable.message ?: "Unknown error")
                }
            )
    }
}
