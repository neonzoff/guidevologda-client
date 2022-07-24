package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.type_entity_layout.view.*
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.URL_CLOUD
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto
import ru.neonzoff.guidevologdaclient.screens.entities.TypeEntityFragment

class TypeEntityAdapter : RecyclerView.Adapter<TypeEntityAdapter.TypeEntityHolder>() {

    private var listTypeEntity = emptyList<TypeEntityDto>()

    class TypeEntityHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeEntityHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.type_entity_layout, parent, false)
        return TypeEntityHolder(view)
    }

    override fun onBindViewHolder(holder: TypeEntityHolder, position: Int) {
        holder.itemView.card_text.text = when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
            RUSSIAN -> listTypeEntity[position].name
            else -> listTypeEntity[position].nameEn
        }
        holder.itemView.card_description.text =
            when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                RUSSIAN -> listTypeEntity[position].description
                else -> listTypeEntity[position].descriptionEn
            }
        Glide.with(holder.itemView.context)
            .load(URL_CLOUD + listTypeEntity[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.card_image)
    }

    override fun getItemCount(): Int {
        return listTypeEntity.size
    }

    fun setList(list: List<TypeEntityDto>) {
        listTypeEntity = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: TypeEntityHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            TypeEntityFragment.clickTypeEntity(listTypeEntity[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: TypeEntityHolder) {
        holder.itemView.setOnClickListener(null)
    }

}