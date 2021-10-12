package com.example.kirozh.andersen_homework_5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * @author Kirill Ozhigin on 10.10.2021
 */

class FragmentContactList : Fragment(R.layout.list_fragment) {

    private var callBacks: CallBacks? = null

    private var contacts = ContactList.contacts

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CallBacks)
            callBacks = context
        else
            throw IllegalArgumentException("Host activity must implement methods")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.contact_item_1).setOnClickListener {
            callBacks?.onTextViewClickListener(0)
        }

        view.findViewById<LinearLayout>(R.id.contact_item_2).setOnClickListener {
            callBacks?.onTextViewClickListener(1)
        }

        view.findViewById<LinearLayout>(R.id.contact_item_3).setOnClickListener {
            callBacks?.onTextViewClickListener(2)
        }

        view.findViewById<LinearLayout>(R.id.contact_item_4).setOnClickListener {
            callBacks?.onTextViewClickListener(3)
        }

        view.findViewById<TextView>(R.id.nameTextView1).text = contacts[0].contactName
        view.findViewById<TextView>(R.id.surnameTextView1).text = contacts[0].contactSurname
        view.findViewById<TextView>(R.id.numberTextView1).text = contacts[0].contactNumber

        view.findViewById<TextView>(R.id.nameTextView2).text = contacts[1].contactName
        view.findViewById<TextView>(R.id.surnameTextView2).text = contacts[1].contactSurname
        view.findViewById<TextView>(R.id.numberTextView2).text = contacts[1].contactNumber

        view.findViewById<TextView>(R.id.nameTextView3).text = contacts[2].contactName
        view.findViewById<TextView>(R.id.surnameTextView3).text = contacts[2].contactSurname
        view.findViewById<TextView>(R.id.numberTextView3).text = contacts[2].contactNumber

        view.findViewById<TextView>(R.id.nameTextView4).text = contacts[3].contactName
        view.findViewById<TextView>(R.id.surnameTextView4).text = contacts[3].contactSurname
        view.findViewById<TextView>(R.id.numberTextView4).text = contacts[3].contactNumber
    }

    override fun onDetach() {
        super.onDetach()
        callBacks = null
    }

    interface CallBacks {
        fun onTextViewClickListener(id: Int)
    }

    companion object {
        fun newInstance(): FragmentContactList {
            return FragmentContactList()
        }
    }
}