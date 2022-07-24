package ru.neonzoff.guidevologdaclient.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.R
import java.lang.ref.WeakReference

class EntityTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var tvEntityName: TextView? = null
    private var tvEntityDescription: TextView? = null
    private var tvEntityAddress: TextView? = null
    private var tvEntityWorkSchedule: TextView? = null

    var name: String = ""
    var description: String = ""
    var address: String = ""
    var workSchedule: String = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        tvEntityName = view.get()?.findViewById(R.id.tv_item_name)
        tvEntityDescription = view.get()?.findViewById(R.id.tv_item_desc)
        tvEntityAddress = view.get()?.findViewById(R.id.tv_item_address)
        tvEntityWorkSchedule = view.get()?.findViewById(R.id.tv_item_work_schedule)
    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(name)
            }
        }
    }

    fun updateView() {
        tvEntityName?.text = name
        tvEntityDescription?.text = description
        tvEntityAddress?.text = address
        tvEntityWorkSchedule?.text = workSchedule

    }

}