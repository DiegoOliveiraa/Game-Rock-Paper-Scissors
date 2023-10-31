package com.diegooliveira.rock_paper_scissors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.MedievalNameViewModel
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.RockPaperScissorsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val medievalNameViewModel: MedievalNameViewModel by viewModel()
    private val rockPaperScissorsViewModel: RockPaperScissorsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}