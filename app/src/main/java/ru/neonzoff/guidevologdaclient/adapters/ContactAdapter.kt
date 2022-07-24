package ru.neonzoff.guidevologdaclient.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_layout.view.*
import ru.neonzoff.guidevologdaclient.APP
import ru.neonzoff.guidevologdaclient.CLIPBOARDMANAGER
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.GUIDEVOLOGDA
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.api.dto.ContactDto
import ru.neonzoff.guidevologdaclient.api.dto.ContactTypeDto

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    private var listContact = emptyList<ContactDto>()
    private var listContactType = emptyList<ContactTypeDto>()

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currentContact = listContact[position]
        holder.itemView.contact_value.text = currentContact.value
        listContactType.forEach { contactType ->
            if (contactType.contacts.contains(currentContact)) {
                holder.itemView.contact_key.text =
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> contactType.name
                        else -> contactType.nameEn
                    }
                holder.itemView.setOnClickListener {
                    CLIPBOARDMANAGER.setPrimaryClip(
                        ClipData.newPlainText(
                            GUIDEVOLOGDA,
                            currentContact.value
                        )
                    )
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> Toast.makeText(
                            APP,
                            "Значение ${contactType.name} было скопировано в буфер обмена",
                            Toast.LENGTH_SHORT
                        ).show()
                        else -> Toast.makeText(
                            APP,
                            "The value ${contactType.name} was copied to the clipboard",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
        }
    }

    fun setListContact(list: List<ContactDto>) {
        listContact = list
        notifyDataSetChanged()
    }

    fun setListContactType(list: List<ContactTypeDto>) {
        listContactType = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}