package ru.neonzoff.guidevologdaclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.home_image_layout.view.*
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.URL_CLOUD
import ru.neonzoff.guidevologdaclient.api.dto.ImageDto

class HomeImageViewPagerAdapter :
    RecyclerView.Adapter<HomeImageViewPagerAdapter.HomeImageHolder>() {

    class HomeImageHolder(view: View) : RecyclerView.ViewHolder(view)

    private var listImages = emptyList<ImageDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeImageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_image_layout, parent, false)
        return HomeImageViewPagerAdapter.HomeImageHolder(view)
    }

    override fun onBindViewHolder(holder: HomeImageHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(URL_CLOUD + listImages[position].name)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.iv_image)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    fun setList(list: List<ImageDto>) {
        listImages = list
        notifyDataSetChanged()
    }
}