package com.diegooliveira.rock_paper_scissors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.data.remote.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Iniciar a Coroutine para buscar dados da API
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.i("Step 1 -", "Criação response")
                val responseName = RetrofitInstance.apiService.getMedievalName()
                Log.i("Step 2 -", responseName.results.toString())

                val responseRPS = RetrofitInstance.apiService.playGame("rock")
                Log.i("Step 3 -", responseRPS.toString())
            } catch (e: Exception) {
                Log.i("Step 4 -", "erro api")
                // Lidar com erros de rede ou outras exceções
            }
        }
    }
}