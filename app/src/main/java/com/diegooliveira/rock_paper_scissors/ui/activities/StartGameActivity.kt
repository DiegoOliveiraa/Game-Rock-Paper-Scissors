package com.diegooliveira.rock_paper_scissors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_opponent
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_player
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

    private var extraNamePlayer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        extraNamePlayer = intent.getStringExtra("PLAYER_NAME")
        initFindViewByIds()
        initObserversViewModel()
        viewModel.fetchOpponentName()
        extraNamePlayer?.let { player ->
            namePlayer.text = getString(activity_start_game_name_player, player)
        }
    }

    private fun initObserversViewModel() {
        viewModel.opponentName.observe(this, Observer { opponent ->
            extraNamePlayer?.let { player ->
                namePlayer.text = getString(activity_start_game_name_player, player)
                descriptionOpponent.text = getString(activity_start_game_name_opponent, opponent)
            }
        })
    }

    private fun initFindViewByIds() {
        namePlayer = findViewById(R.id.tv_name_player)
        descriptionOpponent = findViewById(R.id.text_description_opponent)
        imageOpponentsChoice = findViewById(R.id.image_opponents_choice)
        imageHandRock = findViewById(R.id.image_hand_rock)
        imageHandPaper = findViewById(R.id.image_hand_paper)
        imageHandScissors = findViewById(R.id.image_hand_scissors)
    }
}