package com.diegooliveira.rock_paper_scissors.domain.repository

import com.diegooliveira.rock_paper_scissors.domain.entity.MedievalName

interface MedievalNameRepository {
    suspend fun getMedievalName(): MedievalName
}