package com.example.leapyear.data.datasource

/**
 * Console-based implementation of [YearDataSource] that reads year input from standard input.
 * 
 * This implementation demonstrates the Data Source pattern and can be easily replaced
 * with other implementations (file-based, API-based, etc.) without affecting other layers.
 */
class ConsoleYearDataSource(
    private val prompt: String = "Enter favorite year: ",
    private val inputReader: () -> String? = { readlnOrNull() }
) : YearDataSource {

    /**
     * Reads a year from console input and validates it can be parsed as an integer.
     * 
     * @return Result.success with the parsed year, or Result.failure with error details
     */
    override fun readYear(): Result<Int> {
        print(prompt)
        val input = inputReader()?.trim()
        val year = input?.toIntOrNull()
        return if (year != null) {
            Result.success(year)
        } else {
            Result.failure(IllegalArgumentException("Invalid input"))
        }
    }
}
