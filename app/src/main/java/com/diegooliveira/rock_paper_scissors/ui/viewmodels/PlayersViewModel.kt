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
    val opponentName: LiveData<String> get() = _opponentName
    private val _opponentName = MutableLiveData<String>()

    val playerUpdated: LiveData<Boolean> get() = _playerUpdated
    private val _playerUpdated = MutableLiveData<Boolean>()

    fun fetchOpponentName() {
        viewModelScope.launch {
            try {
                val response = repository.getMedievalName()
                _opponentName.value = response.results.first()
            } catch (e: Exception) {
                Log.i("ERRO API", e.toString())
            }
        }
    }

    fun savePlayer(player: String) {
        if (player.isNotEmpty()) {
            playerDataManager.savePlayerData(PlayerData(player))
            _playerUpdated.value = true
        } else _playerUpdated.value = false
    }

    fun getPlayerList(): List<PlayerData> {
        return playerDataManager.getPlayerList().toList()
    }
}
