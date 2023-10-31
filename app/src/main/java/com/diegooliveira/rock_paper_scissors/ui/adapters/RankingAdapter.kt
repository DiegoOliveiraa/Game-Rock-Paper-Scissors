package com.diegooliveira.rock_paper_scissors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegooliveira.rock_paper_scissors.R
import com.diegooliveira.rock_paper_scissors.data.source.local.PlayerData


class RankingAdapter(
    private val context: Context,
    players: List<PlayerData>
) : RecyclerView
    .Adapter<RankingAdapter.ViewHolder>() {

    private val players = players.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(playerData: PlayerData) {
            val name = itemView.findViewById<TextView>(R.id.text_rank_name)
            name.text = playerData.playerName
            val points = itemView.findViewById<TextView>(R.id.text_rank_points)
            points.text = playerData.points.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater
            .inflate(
                R.layout.item_ranking_player,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = players[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int = players.size

}