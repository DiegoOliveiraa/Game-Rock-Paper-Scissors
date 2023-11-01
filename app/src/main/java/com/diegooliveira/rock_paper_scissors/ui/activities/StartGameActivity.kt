package com.diegooliveira.rock_paper_scissors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_opponent
import com.diegooliveira.rock_paper_scissors.R.string.activity_start_game_name_player
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType.*
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsType.Companion.fromRockPaperScissorsType
import com.diegooliveira.rock_paper_scissors.domain.entity.WinnerType.Companion.fromWinnerType
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
    private lateinit var textRankNamePlayerName: AppCompatTextView
    private lateinit var textRankNamePlayerPoints: AppCompatTextView
    private lateinit var textRankNameOpponentName: AppCompatTextView
    private lateinit var textRankNameOpponentPoints: AppCompatTextView

    private var extraNamePlayer: String? = null
    private var extraNameOpponent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        setUpFindViewByIds()
        setUpTextViewNames()
        initObserversViewModel()
        setOnClickListeners()
        viewModel.initRoundsPlayed()
    }

    private fun setUpTextViewNames() {
        extraNamePlayer = intent.getStringExtra("PLAYER_NAME")
        extraNameOpponent = intent.getStringExtra("OPPONENT_NAME")
        extraNamePlayer?.let { player ->
            namePlayer.text = getString(activity_start_game_name_player, player)
            textRankNamePlayerName.text = player
        }
        extraNameOpponent?.let { opponent ->
            descriptionOpponent.text = getString(activity_start_game_name_opponent, opponent)
            textRankNameOpponentName.text = opponent
        }
    }

    private fun initObserversViewModel() {
        viewModel.finalRoundsPlayed.observe(this) {
            if (it == VICTORY) viewModel.savePlayerPoints(extraNamePlayer.orEmpty())
            if (it == DEFEAT) viewModel.savePlayerPoints(extraNameOpponent.orEmpty())
            Toast.makeText(
                this,
                getString(R.string.activity_start_game_message_final_rounds),
                Toast.LENGTH_LONG
            )
        }

        viewModel.pointsPlayer.observe(this) { points ->
            textRankNamePlayerPoints.text = points.toString()
        }

        viewModel.pointsOpponent.observe(this) { points ->
            textRankNameOpponentPoints.text = points.toString()
        }

        viewModel.gameResult.observe(this) { result ->
            result?.let { result ->
                when (result.winner.fromWinnerType() ?: DRAW) {
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

                imageOpponentsChoice.setImageResource(
                    when (result.cpu.fromRockPaperScissorsType() ?: ROCK) {
                        ROCK -> R.drawable.ic_rock_selected
                        PAPER -> R.drawable.ic_paper_selected
                        SCISSORS -> R.drawable.ic_scissors_selected
                    }
                )

                ivBgMessage.visibility = View.VISIBLE
                textMessage.visibility = View.VISIBLE
            } ?: run {
                Toast.makeText(
                    this,
                    getString(R.string.activity_start_game_message_error),
                    Toast.LENGTH_LONG
                ).show()
            }
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
        textRankNamePlayerName = findViewById(R.id.text_rank_name_player_name)
        textRankNamePlayerPoints = findViewById(R.id.text_rank_name_player_points)
        textRankNameOpponentName = findViewById(R.id.text_rank_name_opponent_name)
        textRankNameOpponentPoints = findViewById(R.id.text_rank_name_opponent_points)
    }


    private fun setOnClickListeners() {
        imageHandRock.setOnClickListener { makeIconsRockPaperScissors(ROCK) }
        imageHandPaper.setOnClickListener { makeIconsRockPaperScissors(PAPER) }
        imageHandScissors.setOnClickListener { makeIconsRockPaperScissors(SCISSORS) }
    }

    private fun makeIconsRockPaperScissors(selectedOption: RockPaperScissorsType) {
        viewModel.playGame(
            guess = selectedOption.tag,
            player = extraNamePlayer.orEmpty(),
            opponent = extraNameOpponent.orEmpty()
        )

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