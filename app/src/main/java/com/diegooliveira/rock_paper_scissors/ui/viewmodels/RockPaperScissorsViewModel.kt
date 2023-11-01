package com.diegooliveira.rock_paper_scissors.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.RockPaperScissorsResult
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType.Companion.fromTag
import com.diegooliveira.rock_paper_scissors.domain.repository.RockPaperScissorsRepository
import kotlinx.coroutines.launch
class RockPaperScissorsViewModel(private val repository: RockPaperScissorsRepository) : ViewModel() {

    val setUpImageView: LiveData<RockPaperScissorsType> get() = _setUpImageView
    private val _setUpImageView = MutableLiveData<RockPaperScissorsType>()

    val gameResult: LiveData<RockPaperScissorsResult> get() = _gameResult
    private val _gameResult = MutableLiveData<RockPaperScissorsResult>()

    fun playGame(guess: String) {
        viewModelScope.launch {
            try {
                val response = repository.playGame(guess)
                _setUpImageView.value = response.cpu.fromTag()
                _gameResult.value = response
            } catch (e: Exception) {
                Log.i("ERRO API", e.toString())
            }
        }
    }
}