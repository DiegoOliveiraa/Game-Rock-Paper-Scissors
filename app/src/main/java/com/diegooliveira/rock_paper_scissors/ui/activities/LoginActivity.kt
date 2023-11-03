package com.diegooliveira.rock_paper_scissors.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.util.NetworkHandler
import com.diegooliveira.rock_paper_scissors.ui.util.DialogUtils.Companion.showCustomDialog
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
        with(viewModel) {
            opponentName.observe(this@LoginActivity) { opponent ->
                viewModel.savePlayer(opponent)
                goToStartGameActivity(opponent)
            }
        }
    }

    private fun setOnClickListeners() {
        buttonStartGame.setOnClickListener {
            goToActivityHandler {
                viewModel.savePlayer(inputTextPlayer.text.toString())
                viewModel.fetchOpponentName()
            }
        }

        buttonRanking.setOnClickListener {
            goToActivityHandler(::goToRankingActivity)
        }
    }

    private fun goToActivityHandler(function: () -> Unit) {
        if (NetworkHandler(this).isInternetAvailable().not()) {
            showCustomDialog(R.string.activity_login_dialog_no_internet_message)
        } else function.invoke()
    }

    private fun goToStartGameActivity(opponent: String?) {
        startActivity(
            Intent(this, StartGameActivity::class.java).apply {
                putExtra("PLAYER_NAME", inputTextPlayer.text.toString())
                putExtra("OPPONENT_NAME", opponent)
            }
        )
    }

    private fun goToRankingActivity() {
        startActivity(Intent(this, RankingActivity::class.java))
    }

}