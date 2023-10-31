package com.diegooliveira.rock_paper_scissors.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlayerDataManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("player_data", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun savePlayerData(playerData: PlayerData) {
        val playerList: MutableList<PlayerData> = getPlayerList()
        if (!playerList.any { it.playerName == playerData.playerName }) {
            playerList.add(playerData)
            savePlayerList(playerList)
        }
    }

    fun getPlayerList(): MutableList<PlayerData> {
        val json = sharedPreferences.getString("players", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<MutableList<PlayerData>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    private fun savePlayerList(playerList: List<PlayerData>) {
        val editor = sharedPreferences.edit()
        editor.putString("players", gson.toJson(playerList))
        editor.apply()
    }

    fun updatePlayerPoints(playerName: String, newPoints: Int): Boolean {
        for (player in getPlayerList()) {
            if (player.playerName == playerName) {
                player.points = newPoints
                savePlayerList(getPlayerList())
                return true
            }
        }
        return false
    }
}
