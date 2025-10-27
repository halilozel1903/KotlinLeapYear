package com.example.leapyear.presentation.state

/**
 * Represents the possible UI states for the leap year flow.
 */
sealed class LeapYearUiState {
    data class Success(val year: Int, val isLeap: Boolean) : LeapYearUiState()
    data class Error(val message: String) : LeapYearUiState()
}
