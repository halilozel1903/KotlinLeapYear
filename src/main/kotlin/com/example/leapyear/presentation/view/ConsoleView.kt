package com.example.leapyear.presentation.view

import com.example.leapyear.presentation.state.LeapYearUiState
import com.example.leapyear.presentation.viewmodel.LeapYearViewModel

/**
 * Console-based view that renders the leap year result.
 * 
 * This view represents the outermost layer of Clean Architecture,
 * delegating business logic to the ViewModel and reacting to UI state changes.
 */
class ConsoleView(
    private val viewModel: LeapYearViewModel
) {

    /**
     * Renders the UI by evaluating the leap year state and displaying the result.
     */
    fun render() {
        when (val state = viewModel.evaluateFavoriteYear()) {
            is LeapYearUiState.Success -> {
                val message = "${state.year} is ${if (state.isLeap) "a leap" else "not a leap"} year."
                println(message)
            }
            is LeapYearUiState.Error -> println("Error: ${state.message}")
        }
    }
}
