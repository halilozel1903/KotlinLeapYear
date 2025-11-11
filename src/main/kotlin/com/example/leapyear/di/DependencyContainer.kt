package com.example.leapyear.di

import com.example.leapyear.data.datasource.ConsoleYearDataSource
import com.example.leapyear.data.datasource.YearDataSource
import com.example.leapyear.data.repository.YearRepositoryImpl
import com.example.leapyear.domain.repository.YearRepository
import com.example.leapyear.domain.usecase.IsLeapYearUseCase
import com.example.leapyear.presentation.view.ConsoleView
import com.example.leapyear.presentation.viewmodel.LeapYearViewModel

/**
 * Simple dependency injection container for the application.
 * 
 * This container manages object creation and dependencies following the
 * Dependency Inversion Principle - high-level modules depend on abstractions,
 * not concrete implementations.
 */
object DependencyContainer {
    
    // Data Layer
    private val yearDataSource: YearDataSource by lazy {
        ConsoleYearDataSource()
    }
    
    private val yearRepository: YearRepository by lazy {
        YearRepositoryImpl(yearDataSource)
    }
    
    // Domain Layer
    private val isLeapYearUseCase: IsLeapYearUseCase by lazy {
        IsLeapYearUseCase()
    }
    
    // Presentation Layer
    private val viewModel: LeapYearViewModel by lazy {
        LeapYearViewModel(yearRepository, isLeapYearUseCase)
    }
    
    val consoleView: ConsoleView by lazy {
        ConsoleView(viewModel)
    }
}
