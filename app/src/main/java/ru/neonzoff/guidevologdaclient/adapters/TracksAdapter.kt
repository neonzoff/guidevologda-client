package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.entity_layout.view.tv_name
import kotlinx.android.synthetic.main.track_layout.view.*
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.URL_CLOUD
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.screens.tracks.TracksFragment

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TrackHolder>() {
    private var listTracks = emptyList<TrackDto>()

    class TrackHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.track_layout, parent, false)
        return TrackHolder(view)
    }

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.itemView.tv_name.text = when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
            RUSSIAN -> listTracks[position].name
            else -> listTracks[position].nameEn
        }
        Glide.with(holder.itemView.context)
            .load(URL_CLOUD + listTracks[position].image.name)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.iv_track)
    }

    override fun getItemCount(): Int {
        return listTracks.size
    }

    fun setList(list: List<TrackDto>) {
        listTracks = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: TrackHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            TracksFragment.clickTrack(listTracks[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: TrackHolder) {
        holder.itemView.setOnClickListener(null)
    }
}