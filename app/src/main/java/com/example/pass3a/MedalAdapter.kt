package com.example.pass3a

import android.view.LayoutInflater
import android.view.View
import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MedalAdapter(
    private val list: List<Medal>,
    private val clickListener: (Medal) -> Unit
) : RecyclerView.Adapter<MedalAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleText)
        val subtitle: TextView = view.findViewById(R.id.subtitleText)
        val total: TextView = view.findViewById(R.id.totalText)
        val icon: ImageView = view.findViewById(R.id.iconImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val medal = list[position]

        holder.title.text = medal.country
        holder.subtitle.text = medal.code
        holder.total.text = medal.total.toString()

        if (medal.total > 150) {

            holder.icon.setImageResource(R.drawable.gold)

            holder.itemView.setBackgroundColor(
                Color.parseColor("#FFF8E1")
            )

        } else if (medal.total > 50) {

            holder.icon.setImageResource(R.drawable.silver)

            holder.itemView.setBackgroundColor(
                Color.WHITE
            )

        } else {

            holder.icon.setImageResource(R.drawable.bronze)

            holder.itemView.setBackgroundColor(
                Color.WHITE
            )
        }

        holder.itemView.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "${medal.country} won ${medal.gold} gold medals",
                Toast.LENGTH_SHORT
            ).show()

            clickListener(medal)
        }
    }
}