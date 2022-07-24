package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.holders.TrackEntitiesViewHolder
import ru.neonzoff.guidevologdaclient.holders.TrackMapViewHolder
import ru.neonzoff.guidevologdaclient.holders.TrackTextViewHolder

class TrackAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        TRACK_MAP, TRACK_TEXT, TRACK_ENTITIES
    }

    //    private var list: MutableList<String> = mutableListOf()
    private var trackName: String = ""
    private var trackDescription: String = ""
    private var trackSectionsCount: Int = 3

    var onLoadMore: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ViewHolderType.TRACK_MAP.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_track_map, parent, false)
                return TrackMapViewHolder(view)
            }
            ViewHolderType.TRACK_TEXT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_track_text, parent, false)
                return TrackTextViewHolder(view)
            }
            ViewHolderType.TRACK_ENTITIES.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_track_entities, parent, false)
                return TrackEntitiesViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_track_map, parent, false)
                return TrackMapViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TrackMapViewHolder -> {
                holder.setContent("mock")
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is TrackTextViewHolder -> {
                holder.name = trackName
                holder.description = trackDescription
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is TrackEntitiesViewHolder -> {
                holder.setContent("mock")
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
        }

        /*if (position == list.size - 1) {
            onLoadMore?.let {
                it()
            }
        }*/

        if (position == trackSectionsCount - 1) {
            onLoadMore?.let {
                it()
            }
        }

    }

//    override fun getItemCount(): Int = list.size

    override fun getItemCount(): Int = trackSectionsCount


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewHolderType.TRACK_MAP.ordinal
            1 -> ViewHolderType.TRACK_TEXT.ordinal
            2 -> ViewHolderType.TRACK_ENTITIES.ordinal
            else -> ViewHolderType.TRACK_MAP.ordinal
        }
        /*return when (list[position]) {
            "Type A" -> ViewHolderType.HOME_IMAGES.ordinal
            "Type B" -> ViewHolderType.HOME_TEXT.ordinal
            "Type C" -> ViewHolderType.TYPE_C.ordinal
            else -> ViewHolderType.HOME_IMAGES.ordinal
        }*/
    }
/*
    fun reload(list: MutableList<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
*/

    fun reloadTrack(
        trackSectionsCount: Int,
        entityName: String,
        entityDescription: String,
    ) {
        this.trackSectionsCount = trackSectionsCount
        this.trackName = entityName
        this.trackDescription = entityDescription
        notifyDataSetChanged()
    }

/*
    fun loadMore(list: MutableList<String>) {
        this.list.addAll(list)
        notifyItemRangeChanged(this.list.size - list.size + 1, list.size)
    }
*/

}