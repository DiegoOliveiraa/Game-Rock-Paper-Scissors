package com.diegooliveira.rock_paper_scissors.ui.activities


import com.diegooliveira.rock_paper_scissors.data.repository.PlayersRepositoryImpl
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.MedievalNameResponse
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
class PlayersRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: PlayersRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = PlayersRepositoryImpl(apiService)
    }

    @Test
    fun `test getMedievalName`() = runTest {
        launch {
            // Arrange
            val mockResponse = MedievalNameResponse(listOf("MedievalName"))
            `when`(apiService.getMedievalName()).thenReturn(mockResponse)

            // Act
            val result = repository.getMedievalName()

            // Assert
            assertEquals("MedievalName", result.results.firstOrNull())
        }
    }
}
