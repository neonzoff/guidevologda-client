package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import java.lang.ref.WeakReference

class HomeTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var tvHomeName: TextView? = null
    private var tvHomeDescription: TextView? = null

    var name: String = ""
    var description: String = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        tvHomeName = view.get()?.findViewById(R.id.tv_home_name)
        tvHomeDescription = view.get()?.findViewById(R.id.tv_home_description)
    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(name)
            }
        }
    }

    fun updateView() {
        tvHomeName?.text = name
        tvHomeDescription?.text = description
    }

}