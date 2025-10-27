package com.example.leapyear.presentation.view

import com.example.leapyear.presentation.state.LeapYearUiState
import com.example.leapyear.presentation.viewmodel.LeapYearViewModel

/**
 * Simple console-based view that renders the leap year result.
 */
class ConsoleView(
    private val viewModel: LeapYearViewModel
) {

    fun render() {
        when (val state = viewModel.evaluateFavoriteYear()) {
            is LeapYearUiState.Success -> {
                val message = "${state.year} is ${if (state.isLeap) "a leap" else "not a leap"} year."
                println(message)
            }
            is LeapYearUiState.Error -> println(state.message)
        }
    }
}
