package com.diegooliveira.rock_paper_scissors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_opponent
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_player
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType.*
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType.Companion.fromWinnerTag
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType.DEFEAT
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType.DRAW
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType.VICTORY
import com.diegooliveira.rock_paper_scissors.ui.viewmodels.RockPaperScissorsViewModel
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartGameActivity : AppCompatActivity() {

    private val viewModel: RockPaperScissorsViewModel by viewModel()

    private lateinit var namePlayer: MaterialTextView
    private lateinit var descriptionOpponent: MaterialTextView
    private lateinit var imageOpponentsChoice: AppCompatImageView
    private lateinit var imageHandRock: AppCompatImageView
    private lateinit var imageHandPaper: AppCompatImageView
    private lateinit var imageHandScissors: AppCompatImageView
    private lateinit var ivBgMessage: AppCompatImageView
    private lateinit var textMessage: MaterialTextView

    private var extraNamePlayer: String? = null
    private var extraNameOpponent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        setUpFindViewByIds()
        setUpTextViewNames()
        initObserversViewModel()
        setOnClickListeners()
    }

    private fun setUpTextViewNames() {
        extraNamePlayer = intent.getStringExtra("PLAYER_NAME")
        extraNameOpponent = intent.getStringExtra("OPPONENT_NAME")
        extraNamePlayer?.let { player ->
            namePlayer.text = getString(activity_start_game_name_player, player)
        }
        extraNameOpponent?.let { opponent ->
            descriptionOpponent.text = getString(activity_start_game_name_opponent, opponent)
        }
    }

    private fun initObserversViewModel() {
        viewModel.gameResult.observe(this) { result ->
            when (result.winner.fromWinnerTag() ?: DRAW) {
                VICTORY -> {
                    ivBgMessage.setImageResource(R.drawable.bg_message_victory)
                    textMessage.text = getString(R.string.activity_start_game_message_victory)
                    textMessage.setTextAppearance(R.style.TextViewMessageStyle_Victory)

                }

                DEFEAT -> {
                    ivBgMessage.setImageResource(R.drawable.bg_message_defeat)
                    textMessage.text = getString(R.string.activity_start_game_message_defeat)
                    textMessage.setTextAppearance(R.style.TextViewMessageStyle_Defeat)
                }

                DRAW -> {
                    ivBgMessage.setImageResource(R.drawable.bg_message_draw)
                    textMessage.text = getString(R.string.activity_start_game_message_draw)
                    textMessage.setTextAppearance(R.style.TextViewMessageStyle_Draw)
                }
            }
            val (bgResource, messageResource) = when (result.winner.fromWinnerTag() ?: DRAW) {
                VICTORY -> R.drawable.bg_message_victory to R.string.activity_start_game_message_victory
                DEFEAT -> R.drawable.bg_message_defeat to R.string.activity_start_game_message_defeat
                DRAW -> R.drawable.bg_message_draw to R.string.activity_start_game_message_draw
            }

            ivBgMessage.setImageResource(bgResource)
            textMessage.text = getString(messageResource)
            ivBgMessage.visibility = View.VISIBLE
            textMessage.visibility = View.VISIBLE
        }
        viewModel.setUpImageView.observe(this) {
            imageOpponentsChoice.setImageResource(
                when (it) {
                    ROCK -> R.drawable.ic_rock_selected
                    PAPER -> R.drawable.ic_paper_selected
                    SCISSORS -> R.drawable.ic_scissors_selected
                }
            )
        }
    }

    private fun setUpFindViewByIds() {
        namePlayer = findViewById(R.id.tv_name_player)
        descriptionOpponent = findViewById(R.id.text_description_opponent)
        imageOpponentsChoice = findViewById(R.id.image_opponents_choice)
        imageHandRock = findViewById(R.id.image_hand_rock)
        imageHandPaper = findViewById(R.id.image_hand_paper)
        imageHandScissors = findViewById(R.id.image_hand_scissors)
        ivBgMessage = findViewById(R.id.iv_bg_message)
        textMessage = findViewById(R.id.text_message)
    }


    private fun setOnClickListeners() {
        imageHandRock.setOnClickListener { makeIconsRockPaperScissors(ROCK) }
        imageHandPaper.setOnClickListener { makeIconsRockPaperScissors(PAPER) }
        imageHandScissors.setOnClickListener { makeIconsRockPaperScissors(SCISSORS) }
    }

    private fun makeIconsRockPaperScissors(selectedOption: RockPaperScissorsType) {
        viewModel.playGame(selectedOption.tag)
        when (selectedOption) {
            ROCK -> {
                imageHandRock.setImageResource(R.drawable.ic_rock_selected)
                imageHandPaper.setImageResource(R.drawable.ic_paper_disabled)
                imageHandScissors.setImageResource(R.drawable.ic_scissors_disabled)
            }

            PAPER -> {
                imageHandRock.setImageResource(R.drawable.ic_rock_disabled)
                imageHandPaper.setImageResource(R.drawable.ic_paper_selected)
                imageHandScissors.setImageResource(R.drawable.ic_scissors_disabled)
            }

            SCISSORS -> {
                imageHandRock.setImageResource(R.drawable.ic_rock_disabled)
                imageHandPaper.setImageResource(R.drawable.ic_paper_disabled)
                imageHandScissors.setImageResource(R.drawable.ic_scissors_selected)

            }
        }
    }

}