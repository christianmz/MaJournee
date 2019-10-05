package com.example.majournee.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majournee.R
import com.example.majournee.inflate
import com.example.majournee.models.Song
import kotlinx.android.synthetic.main.cardview_song.view.*

class MusicAdapter(private val list: List<Song>) :
    RecyclerView.Adapter<MusicAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(parent.inflate(R.layout.cardview_song))

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(song: Song) =
            with(itemView) {
                tv_name_song.text = song.name
                tv_artist.text = song.artist
            }
    }
}

