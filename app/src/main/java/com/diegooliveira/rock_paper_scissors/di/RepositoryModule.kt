package com.diegooliveira.rock_paper_scissors.di

import com.diegooliveira.rock_paper_scissors.data.repository.PlayersRepositoryImpl
import com.diegooliveira.rock_paper_scissors.data.repository.RockPaperScissorsRepositoryImpl
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.domain.repository.PlayersRepository
import com.diegooliveira.rock_paper_scissors.domain.repository.RockPaperScissorsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single<PlayersRepository> { PlayersRepositoryImpl(get()) }
    single<RockPaperScissorsRepository> { RockPaperScissorsRepositoryImpl(get()) }
    single { get<Retrofit>().create(ApiService::class.java) }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.toys/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}