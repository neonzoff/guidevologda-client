package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.api.dto.ImageDto
import ru.neonzoff.guidevologdaclient.holders.HomeButtonViewHolder
import ru.neonzoff.guidevologdaclient.holders.HomeImagesViewHolder
import ru.neonzoff.guidevologdaclient.holders.HomeTextViewHolder

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        HOME_IMAGES, HOME_TEXT, HOME_BUTTON
    }

    //    private var list: MutableList<String> = mutableListOf()
    private var homeImages: MutableList<ImageDto> = mutableListOf()
    private var homeName: String = ""
    private var homeDescription: String = ""
    private var homeSectionsCount: Int = 3

    var onLoadMore: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ViewHolderType.HOME_IMAGES.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_home_images, parent, false)
                return HomeImagesViewHolder(view)
            }
            ViewHolderType.HOME_TEXT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_home_text, parent, false)
                return HomeTextViewHolder(view)
            }
            ViewHolderType.HOME_BUTTON.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_home_button, parent, false)
                return HomeButtonViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_home_images, parent, false)
                return HomeImagesViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeImagesViewHolder -> {
                holder.setContent("mock")
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is HomeTextViewHolder -> {
                holder.name = homeName
                holder.description = homeDescription
                holder.onItemClick = {
                    Toast.makeText(holder.itemView.context, it, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
            is HomeButtonViewHolder -> {
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

        if (position == homeSectionsCount - 1) {
            onLoadMore?.let {
                it()
            }
        }

    }

//    override fun getItemCount(): Int = list.size

    override fun getItemCount(): Int = homeSectionsCount


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewHolderType.HOME_IMAGES.ordinal
            1 -> ViewHolderType.HOME_TEXT.ordinal
            2 -> ViewHolderType.HOME_BUTTON.ordinal
            else -> ViewHolderType.HOME_IMAGES.ordinal
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

    fun reloadHome(
        homeCount: Int,
        homeImages: List<ImageDto>,
        homeName: String,
        homeDescription: String
    ) {
        this.homeImages.clear()
        this.homeImages.addAll(homeImages)
        this.homeSectionsCount = homeCount
        this.homeName = homeName
        this.homeDescription = homeDescription
        notifyDataSetChanged()
    }

/*
    fun loadMore(list: MutableList<String>) {
        this.list.addAll(list)
        notifyItemRangeChanged(this.list.size - list.size + 1, list.size)
    }
*/

}