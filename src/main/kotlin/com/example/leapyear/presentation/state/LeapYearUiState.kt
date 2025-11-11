package com.example.leapyear.presentation.state

/**
 * Represents the possible UI states for the leap year feature.
 * 
 * Using sealed classes ensures exhaustive when expressions and type-safe state management.
 * This is a common pattern in modern Android development (similar to MVI architecture).
 */
sealed class LeapYearUiState {
    /**
     * Successful evaluation of a year's leap year status.
     * 
     * @param year The year that was evaluated
     * @param isLeap Whether the year is a leap year
     */
    data class Success(val year: Int, val isLeap: Boolean) : LeapYearUiState()
    
    /**
     * Error state when year cannot be evaluated.
     * 
     * @param message Human-readable error message
     */
    data class Error(val message: String) : LeapYearUiState()
}
