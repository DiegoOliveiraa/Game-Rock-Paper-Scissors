package com.diegooliveira.rock_paper_scissors.data.repository

import com.diegooliveira.rock_paper_scissors.data.mapper.MedievalNameMapper
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.domain.entity.MedievalName
import com.diegooliveira.rock_paper_scissors.domain.repository.PlayersRepository

class PlayersRepositoryImpl(private val apiService: ApiService) : PlayersRepository {
    override suspend fun getMedievalName(): MedievalName {
        return MedievalNameMapper.mapResponseToModel(apiService.getMedievalName())
    }
}