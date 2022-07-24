package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.entity_in_track_layout.view.*
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto

class EntityInTrackAdapter : RecyclerView.Adapter<EntityInTrackAdapter.EntityInTrackHolder>() {

    class EntityInTrackHolder(view: View) : RecyclerView.ViewHolder(view)

    private var listEntities = emptyList<BaseEntityDto>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntityInTrackAdapter.EntityInTrackHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.entity_in_track_layout, parent, false)
        return EntityInTrackAdapter.EntityInTrackHolder(view)
    }

    override fun onBindViewHolder(holder: EntityInTrackAdapter.EntityInTrackHolder, position: Int) {
        val currentEntity = listEntities[position]
        holder.itemView.entity_name.text = when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
            RUSSIAN -> listEntities[position].name
            else -> listEntities[position].nameEn
        }
    }

    fun setList(list: List<BaseEntityDto>) {
        listEntities = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listEntities.size
    }
}