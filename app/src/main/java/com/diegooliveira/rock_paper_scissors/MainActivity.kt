package com.diegooliveira.rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                val response = RetrofitInstance.apiService.getDogBreeds()
                Log.i("Step 2 -", response.status)
                if (response.status == "success") {
                    val breedsMap = response.message
                    Log.i("Step 3 -", response.message.toString())
                    // Agora você pode usar a lista de raças de cachorro (breedsMap) como quiser
                    // Por exemplo, exibir em um RecyclerView ou ListView
                } else {
                    Log.i("Step 4 -", "nada aqui")
                    // Tratar erro, se necessário
                }
            } catch (e: Exception) {
                Log.i("Step 5 -", "erro api")
                // Lidar com erros de rede ou outras exceções
            }
        }
    }
}