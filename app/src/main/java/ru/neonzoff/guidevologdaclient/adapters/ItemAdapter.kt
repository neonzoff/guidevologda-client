package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.api.dto.ImageDto
import ru.neonzoff.guidevologdaclient.holders.EntityButtonViewHolder
import ru.neonzoff.guidevologdaclient.holders.EntityContactsViewHolder
import ru.neonzoff.guidevologdaclient.holders.EntityImagesViewHolder
import ru.neonzoff.guidevologdaclient.holders.EntityTextViewHolder
import ru.neonzoff.guidevologdaclient.positions

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        ENTITY_IMAGES, ENTITY_TEXT, ENTITY_CONTACTS, ENTITY_TO_MAP_BUTTON
    }

    //    private var list: MutableList<String> = mutableListOf()
    private var entityImages: MutableList<ImageDto> = mutableListOf()
    private var entityName: String = ""
    private var entityDescription: String = ""
    private var entityAddress: String = ""
    private var entityPos: String = ""
    private var entityWorkSchedule: String = ""
    private var entitySectionsCount: Int = 4

    var onLoadMore: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ViewHolderType.ENTITY_IMAGES.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_entity_images, parent, false)
                return EntityImagesViewHolder(view)
            }
            ViewHolderType.ENTITY_TEXT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_entity_text, parent, false)
                return EntityTextViewHolder(view)
            }
            ViewHolderType.ENTITY_CONTACTS.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_entity_contacts, parent, false)
                return EntityContactsViewHolder(view)
            }
            ViewHolderType.ENTITY_TO_MAP_BUTTON.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_entity_button, parent, false)
                return EntityButtonViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_entity_images, parent, false)
                return EntityImagesViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EntityImagesViewHolder -> {
                holder.setContent("mock")
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is EntityTextViewHolder -> {
                holder.name = entityName
                holder.description = entityDescription
                holder.address = entityAddress
                holder.workSchedule = entityWorkSchedule
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is EntityContactsViewHolder -> {
                holder.setContent("mock")
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is EntityButtonViewHolder -> {
                holder.pos = entityPos
                holder.onItemClick = {
                    positions = holder.pos.split(' ')
                    Toast.makeText(
                        holder.itemView.context,
                        R.string.clicked_on_button_to_map,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                holder.updateView()
            }
        }

        /*if (position == list.size - 1) {
            onLoadMore?.let {
                it()
            }
        }*/

        if (position == entitySectionsCount - 1) {
            onLoadMore?.let {
                it()
            }
        }

    }

//    override fun getItemCount(): Int = list.size

    override fun getItemCount(): Int = entitySectionsCount


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewHolderType.ENTITY_IMAGES.ordinal
            1 -> ViewHolderType.ENTITY_TEXT.ordinal
            2 -> ViewHolderType.ENTITY_CONTACTS.ordinal
            3 -> ViewHolderType.ENTITY_TO_MAP_BUTTON.ordinal
            else -> ViewHolderType.ENTITY_IMAGES.ordinal
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

    fun reloadAddressOfEntity(street: String, houseNumber: String) {
        this.entityAddress = "$street $houseNumber"
    }

    fun reloadEntity(
        entityCount: Int,
        entityImages: List<ImageDto>,
        entityName: String,
        entityDescription: String,
        entityAddress: String,
        entityWorkSchedule: String,
        entityPos: String
    ) {
        this.entityImages.clear()
        this.entityImages.addAll(entityImages)
        this.entitySectionsCount = entityCount
        this.entityName = entityName
        this.entityDescription = entityDescription
        this.entityAddress = entityAddress
        this.entityWorkSchedule = entityWorkSchedule
        this.entityPos = entityPos
        notifyDataSetChanged()
    }

/*
    fun loadMore(list: MutableList<String>) {
        this.list.addAll(list)
        notifyItemRangeChanged(this.list.size - list.size + 1, list.size)
    }
*/

}