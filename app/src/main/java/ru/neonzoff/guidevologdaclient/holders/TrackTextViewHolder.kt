package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import java.lang.ref.WeakReference

class TrackTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var tvTrackName: TextView? = null
    private var tvTrackDescription: TextView? = null

    var name: String = ""
    var description: String = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        tvTrackName = view.get()?.findViewById(R.id.tv_track_name)
        tvTrackDescription = view.get()?.findViewById(R.id.tv_track_desc)
    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(name)
            }
        }
    }

    fun updateView() {
        tvTrackName?.text = name
        tvTrackDescription?.text = description
    }

}