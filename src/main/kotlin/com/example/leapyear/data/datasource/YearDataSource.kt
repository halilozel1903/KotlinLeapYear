package com.example.leapyear.data.datasource

/**
 * Defines the contract for retrieving the user's preferred year from any data source.
 * 
 * This interface follows the Interface Segregation Principle and allows for
 * different implementations (console, file, API, etc.) without changing the domain logic.
 */
fun interface YearDataSource {
    /**
     * Reads a year value from the underlying data source.
     *
     * @return a [Result] containing the parsed year or an error describing why it could not be obtained.
     */
    fun readYear(): Result<Int>
}
