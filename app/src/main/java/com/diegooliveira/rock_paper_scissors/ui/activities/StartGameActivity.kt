package com.diegooliveira.rock_paper_scissors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.PlayersViewModel
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartGameActivity : AppCompatActivity() {

    private val viewModel: PlayersViewModel by viewModel()

    private lateinit var namePlayer: MaterialTextView
    private lateinit var descriptionOpponent: MaterialTextView
    private lateinit var imageOpponentsChoice: AppCompatImageView
    private lateinit var imageHandRock: AppCompatImageView
    private lateinit var imageHandPaper: AppCompatImageView
    private lateinit var imageHandScissors: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        intFindViewByIds()
        viewModel.fetchOpponentName()
    }

    private fun intFindViewByIds() {
        namePlayer = findViewById(R.id.tv_name_player)
        descriptionOpponent = findViewById(R.id.text_description_opponent)
        imageOpponentsChoice = findViewById(R.id.image_opponents_choice)
        imageHandRock = findViewById(R.id.image_hand_rock)
        imageHandPaper = findViewById(R.id.image_hand_paper)
        imageHandScissors = findViewById(R.id.image_hand_scissors)
    }
}