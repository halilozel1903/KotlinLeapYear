package com.example.leapyear

import com.example.leapyear.data.datasource.ConsoleYearDataSource
import com.example.leapyear.data.repository.YearRepositoryImpl
import com.example.leapyear.domain.usecase.IsLeapYearUseCase
import com.example.leapyear.presentation.view.ConsoleView
import com.example.leapyear.presentation.viewmodel.LeapYearViewModel

/**
 * Application entry point wired with a simple clean architecture stack.
 */
fun main() {
    val dataSource = ConsoleYearDataSource()
    val repository = YearRepositoryImpl(dataSource)
    val viewModel = LeapYearViewModel(repository, IsLeapYearUseCase())

    ConsoleView(viewModel).render()
}
