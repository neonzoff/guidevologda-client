package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class HomeButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
//                it(name)
            }
        }
    }

    fun updateView() {
    }

}