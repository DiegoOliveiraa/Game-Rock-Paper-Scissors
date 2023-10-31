package com.diegooliveira.rock_paper_scissors.di

import com.diegooliveira.rock_paper_scissors.data.source.local.PlayerDataManager
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.MedievalNameViewModel
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.RockPaperScissorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MedievalNameViewModel(get(), get()) }
    viewModel { RockPaperScissorsViewModel(get()) }
    single { PlayerDataManager(get()) }
}