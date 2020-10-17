package buu.supakin.mathgameverdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.database.PlayerTable

class RankingAdapter: RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    var data = listOf<PlayerTable>()
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

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textRanking: TextView = itemView.findViewById(R.id.text_ranking)
        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textScore: TextView = itemView.findViewById(R.id.text_score)

        fun bind(item: PlayerTable, rank: Int) {
            textRanking.text = rank.toString()
            textName.text = item.name
            textScore.text = (item.scoreCorrect - item.scoreInCorrect).toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_ranking_player,parent, false)
                return ViewHolder(view)
            }
        }
    }
}