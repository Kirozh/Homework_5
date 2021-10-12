package com.example.kirozh.andersen_homework_5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * @author Kirill Ozhigin on 10.10.2021
 */

const val DETAIL_CONTACT_ID = "contact_id"

class FragmentContactDetail : Fragment(R.layout.detail_fragment) {
    private lateinit var nameTV: TextView
    private lateinit var surnameTV: TextView
    private lateinit var numberTV: TextView
    private lateinit var fab: FloatingActionButton

    private var detailCallBacks: DetailCallBacks? = null

    private var contactId: Int = 0

    var contacts = ContactList.contacts

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is DetailCallBacks)
            detailCallBacks = context
        else
            throw IllegalArgumentException("Host activity must implement methods")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        contactId = arguments?.getInt(DETAIL_CONTACT_ID) as Int
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTV = view.findViewById(R.id.fragment_detail_name)
        surnameTV = view.findViewById(R.id.fragment_detail_surname)
        numberTV = view.findViewById(R.id.fragment_detail_number)
        fab = view.findViewById(R.id.floatingActionButton)

        fab.setOnClickListener {
            detailCallBacks?.onEditContactClickListener(contactId)
        }

        nameTV.text = contacts[contactId].contactName
        surnameTV.text = contacts[contactId].contactSurname
        numberTV.text = contacts[contactId].contactNumber
    }

    override fun onDetach() {
        super.onDetach()
        detailCallBacks = null
    }

    interface DetailCallBacks {
        fun onEditContactClickListener(id: Int)
    }

    companion object {

        fun newInstance(contact_id: Int): FragmentContactDetail {
            val args = Bundle().apply {
                putInt(DETAIL_CONTACT_ID, contact_id)
            }
            return FragmentContactDetail().apply {
                arguments = args
            }
        }
    }
}