package com.diegooliveira.rock_paper_scissors.data.repository

import com.diegooliveira.rock_paper_scissors.data.mapper.MedievalNameMapper
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.domain.entity.MedievalName
import com.diegooliveira.rock_paper_scissors.domain.repository.MedievalNameRepository

class MedievalNameRepositoryImpl(private val apiService: ApiService) : MedievalNameRepository {
    override suspend fun getMedievalName(): MedievalName {
        return MedievalNameMapper.mapResponseToModel(apiService.getMedievalName())
    }
}