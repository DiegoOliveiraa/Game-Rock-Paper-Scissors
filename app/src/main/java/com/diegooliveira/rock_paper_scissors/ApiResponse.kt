package com.diegooliveira.rock_paper_scissors

data class ApiResponse(
    val message: Map<String, List<String>>,
    val status: String
)