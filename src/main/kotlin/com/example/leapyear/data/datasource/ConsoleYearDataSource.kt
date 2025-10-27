package com.example.leapyear.data.datasource

/**
 * Reads the preferred year from the console.
 */
class ConsoleYearDataSource(
    private val prompt: String = "Enter favorite year: ",
    private val inputReader: () -> String? = { readlnOrNull() }
) : YearDataSource {

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
