package com.diegooliveira.rock_paper_scissors.data.mapper

import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.MedievalNameResponse
import com.diegooliveira.rock_paper_scissors.domain.entity.MedievalName

object MedievalNameMapper {
    fun mapResponseToModel(response: MedievalNameResponse): MedievalName {
        return MedievalName(response.results)
    }
}