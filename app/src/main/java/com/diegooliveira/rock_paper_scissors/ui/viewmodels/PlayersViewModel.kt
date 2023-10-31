package com.diegooliveira.rock_paper_scissors.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegooliveira.rock_paper_scissors.data.source.local.PlayerData
import com.diegooliveira.rock_paper_scissors.data.source.local.PlayerDataManager
import com.diegooliveira.rock_paper_scissors.domain.repository.PlayersRepository
import kotlinx.coroutines.launch

class PlayersViewModel(
    private val repository: PlayersRepository,
    private val playerDataManager: PlayerDataManager
) : ViewModel() {

    val medievalNames: LiveData<List<String>> get() = _medievalNames
    private val _medievalNames = MutableLiveData<List<String>>()

    val playerUpdated: LiveData<Boolean> get() = _playerUpdated
    private val _playerUpdated = MutableLiveData<Boolean>()

    fun fetchMedievalNames() {
        viewModelScope.launch {
            try {
                val response = repository.getMedievalName()
                _medievalNames.value = response.results
            } catch (e: Exception) {
                Log.i("ERRO API", e.toString())
            }
        }
    }

    fun savePlayer(player: String) {
        if (player.isNotEmpty()) {
            playerDataManager.savePlayerData(PlayerData(player))
            _playerUpdated.value = true
        } else  _playerUpdated.value = false
    }

    fun getPlayerList(): MutableList<PlayerData> {
        return playerDataManager.getPlayerList()
    }
}