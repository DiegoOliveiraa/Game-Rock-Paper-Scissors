package com.diegooliveira.rock_paper_scissors.data.source.local

import android.content.Context
import android.util.Log
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
            val playerList: MutableList<PlayerData> =
                gson.fromJson(json, object : TypeToken<MutableList<PlayerData>>() {}.type)

            val sortedList = playerList.sortedByDescending { it.points }
            val topPlayers = sortedList.take(8).toMutableList()

            val topPlayersJson = gson.toJson(topPlayers)
            sharedPreferences.edit().putString("players", topPlayersJson).apply()

            return topPlayers
        } else {
            mutableListOf()
        }
    }

    private fun savePlayerList(playerList: List<PlayerData>) {
        val editor = sharedPreferences.edit()
        editor.putString("players", gson.toJson(playerList))
        editor.apply()
    }

    fun getPlayerPoints(playerName: String): Int {
        for (player in getPlayerList()) {
            if (player.playerName == playerName) {
                return player.points
            }
        }
        return 0
    }

    fun updatePlayerPoints(playerName: String) {
        val points = getPlayerPoints(playerName)
        deletePlayer(playerName)
        savePlayerData(PlayerData(playerName, points + 1))
    }

    private fun deletePlayer(playerName: String) {
        val playerList = getPlayerList()
        playerList.removeIf { it.playerName == playerName }
        savePlayerList(playerList)
    }
}
