package com.diegooliveira.rock_paper_scissors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.PlayersViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: PlayersViewModel by viewModel()

    private lateinit var buttonStartGame: MaterialButton
    private lateinit var buttonRanking: MaterialButton
    private lateinit var inputTextPlayer: TextInputEditText

    private fun intFindViewByIds() {
        buttonStartGame = findViewById(R.id.bt_start_game)
        buttonRanking = findViewById(R.id.bt_ranking)
        inputTextPlayer = findViewById(R.id.tiet_player)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        intFindViewByIds()
        initObservers()
        setOnClickListeners()
    }

    private fun initObservers() {
        viewModel.opponentName.observe(this) { opponent ->
            viewModel.savePlayer(opponent)
            startActivity(
                Intent(this, StartGameActivity::class.java).apply {
                    putExtra("PLAYER_NAME", inputTextPlayer.text.toString())
                    putExtra("OPPONENT_NAME", opponent)
                }
            )
        }
    }

    private fun setOnClickListeners() {
        buttonStartGame.setOnClickListener {
            viewModel.savePlayer(inputTextPlayer.text.toString())
            viewModel.fetchOpponentName()
        }

        buttonRanking.setOnClickListener {
            startActivity(Intent(this, RankingActivity::class.java))
        }
    }

}