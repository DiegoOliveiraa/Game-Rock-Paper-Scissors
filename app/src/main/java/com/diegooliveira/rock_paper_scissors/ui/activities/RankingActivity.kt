package com.diegooliveira.rock_paper_scissors.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.adapters.RankingAdapter
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.PlayersViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
class RankingActivity : AppCompatActivity() {

    private val viewModel: PlayersViewModel by viewModel()

    lateinit var buttonStartGame: MaterialButton
    lateinit var recyclerView: RecyclerView

    private fun intFindViewByIds() {
        buttonStartGame = findViewById(R.id.bt_ranking)
        recyclerView = findViewById(R.id.activity_lista_produtos_recyclerView)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking_player)
        intFindViewByIds()
        createdRecyclerView()
        buttonStartGame.setOnClickListener {
            finish()
        }
    }

    private fun createdRecyclerView() {
        val dataSet = viewModel.getPlayerList()

        // Configurar o RecyclerView com o Adapter
        val adapter = RankingAdapter(this, dataSet)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}

