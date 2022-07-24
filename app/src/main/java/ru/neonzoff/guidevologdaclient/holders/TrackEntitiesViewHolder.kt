package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class TrackEntitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var textView: TextView? = null

    private var contentC = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
//        textView = view.get()?.findViewById(R.id.textView)
    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(contentC)
            }
        }
    }

    fun setContent(content: String) {
        this.contentC = content
    }

    fun updateView() {
//        textView?.text = contentC
    }

}