package buu.supakin.mathgameverdatabase.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.database.Player

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    var data = listOf<Player>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position + 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textRanking: TextView = itemView.findViewById(R.id.text_ranking)
        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textScore: TextView = itemView.findViewById(R.id.text_empty)

        @SuppressLint("SetTextI18n")
        fun bind(item: Player, rank: Int) {
            val res = itemView.context.resources
            textRanking.text = rank.toString()
            textName.text = item.getName()
            textScore.text = res.getString(R.string.ranking_score, item.getScoreCorrect() - item.getScoreInCorrect(), item.getScoreCorrect(), item.getScoreInCorrect())

            if (rank == 1 || rank == 2 || rank == 3) {
                textRanking.setTextColor(res.getColor(R.color.colorMain))
                textName.setTextColor(res.getColor(R.color.colorMain))
            } else {
                textRanking.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_ranking_player, parent, false)
                return ViewHolder(view)
            }
        }
    }
}