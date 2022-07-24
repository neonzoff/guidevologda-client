package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import java.lang.ref.WeakReference

class EntityButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var btnToMap: Button? = null

    var pos: String = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        btnToMap = view.get()?.findViewById(R.id.show_on_map_btn)
    }

    private fun setListener() {
        btnToMap?.setOnClickListener {
            onItemClick?.let {
                it(pos)
            }
        }
    }

    fun updateView() {
    }

}