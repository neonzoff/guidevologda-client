package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.entity_layout.view.*
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.URL_CLOUD
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.screens.entities.EntityFragment

class EntityAdapter : RecyclerView.Adapter<EntityAdapter.EntityHolder>() {
    private var listEntity = emptyList<BaseEntityDto>()

    class EntityHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.entity_layout, parent, false)
        return EntityHolder(view)
    }

    override fun onBindViewHolder(holder: EntityHolder, position: Int) {
        holder.itemView.tv_name.text = when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
            RUSSIAN -> listEntity[position].name
            else -> listEntity[position].nameEn
        }
        Glide.with(holder.itemView.context)
            .load(URL_CLOUD + listEntity[position].images.random().name)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.iv_entity)
    }

    override fun getItemCount(): Int {
        return listEntity.size
    }

    fun setList(list: List<BaseEntityDto>) {
        listEntity = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: EntityHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            EntityFragment.clickEntity(listEntity[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: EntityHolder) {
        holder.itemView.setOnClickListener(null)
    }
}