package com.example.leapyear

import com.example.leapyear.di.DependencyContainer

/**
 * Application entry point following Clean Architecture principles.
 * 
 * Clean Architecture layers:
 * - Presentation: View, ViewModel, UI State
 * - Domain: Use Cases, Repository Interfaces, Entities
 * - Data: Repository Implementations, Data Sources
 * 
 * The dependency flow goes from outer layers (UI) to inner layers (Domain),
 * with dependencies injected through the DependencyContainer.
 */
fun main() {
    DependencyContainer.consoleView.render()
}
