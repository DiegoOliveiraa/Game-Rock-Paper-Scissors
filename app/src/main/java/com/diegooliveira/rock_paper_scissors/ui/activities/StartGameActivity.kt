package com.diegooliveira.rock_paper_scissors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.MedievalNameViewModel
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.RockPaperScissorsViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartGameActivity : AppCompatActivity() {

    private val medievalNameViewModel: MedievalNameViewModel by viewModel()
    private val rockPaperScissorsViewModel: RockPaperScissorsViewModel by viewModel()

    private lateinit var buttonStartGame: MaterialButton
    private lateinit var inputTextPlayer: TextInputEditText

    private fun intFindViewByIds() {
        buttonStartGame = findViewById(R.id.bt_start_game)
        inputTextPlayer = findViewById(R.id.tiet_player)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        intFindViewByIds()
        setOnClickListeners()

        // Observar as mudanças nos dados e reagir conforme necessário
        medievalNameViewModel.medievalNames.observe(this, Observer { medievalNames ->
            // Atualizar a UI com os nomes medievais
            Log.i("Step 1 - Medieval Names", medievalNames.toString())
        })

        rockPaperScissorsViewModel.gameResult.observe(this, Observer { gameResult ->
            // Atualizar a UI com o resultado do jogo Rock-Paper-Scissors
            Log.i("Step 2 - Game Result", gameResult.toString())
        })

        // Iniciar a Coroutine para buscar dados da API
        medievalNameViewModel.fetchMedievalNames()
        rockPaperScissorsViewModel.playGame("rock")
    }

    private fun setOnClickListeners() {
        buttonStartGame.setOnClickListener {
            val playerUpdated = medievalNameViewModel.updatePlayerPoints(
                playerName = inputTextPlayer.text.toString()
            )

            if (playerUpdated) {
                // Jogador encontrado e pontos atualizados com sucesso
                Toast.makeText(
                    this,
                    "Pontos do jogador atualizados com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Jogador não encontrado na lista
                Toast.makeText(this, "Jogador não encontrado na lista!", Toast.LENGTH_SHORT).show()
                medievalNameViewModel.savePlayer(inputTextPlayer.text.toString())
            }
        }
    }

}