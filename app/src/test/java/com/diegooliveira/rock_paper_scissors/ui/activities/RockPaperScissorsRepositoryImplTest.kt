package com.diegooliveira.rock_paper_scissors.ui.activities

import com.diegooliveira.rock_paper_scissors.data.repository.RockPaperScissorsRepositoryImpl
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.RockPaperScissorsResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RockPaperScissorsRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: RockPaperScissorsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = RockPaperScissorsRepositoryImpl(apiService)
    }

    @Test
    fun `test playGame with player winner`() = runTest {
        launch {
            // Arrange
            val mockResponse = RockPaperScissorsResponse(
                "paper",
                "rock",
                "cpu",
                "paper covers rock"
            )
            `when`(apiService.playGame("scissors")).thenReturn(mockResponse)

            // Act
            val result = repository.playGame("scissors")

            // Assert
            assertEquals("scissors", result.player)
        }
    }


    @Test

    fun `test playGame with cpu winner`() = runTest {
        launch {
            // Arrange
            val mockResponse = RockPaperScissorsResponse(
                "paper",
                "rock",
                "cpu",
                "paper covers rock"
            )
            `when`(
                apiService.playGame("paper")
            ).thenReturn(mockResponse)
            // Act
            val result = repository.playGame("paper")

            // Assert
            assertEquals("paper", result.cpu)
        }
    }
}
