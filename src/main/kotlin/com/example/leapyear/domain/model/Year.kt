package com.example.leapyear.domain.model

/**
 * Domain entity representing a year with validation logic.
 * 
 * This entity encapsulates the year value and ensures it's valid.
 */
data class Year(val value: Int) {
    init {
        require(value > 0) { "Year must be a positive number" }
    }
    
    /**
     * Determines if this year is a leap year.
     * 
     * A leap year is divisible by 4, except for years divisible by 100 
     * unless they are also divisible by 400.
     */
    fun isLeapYear(): Boolean =
        (value % 4 == 0) && (value % 100 != 0 || value % 400 == 0)
}
