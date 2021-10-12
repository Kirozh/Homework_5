package com.example.kirozh.andersen_homework_5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

/**
 * @author Kirill Ozhigin on 10.10.2021
 */


const val EDIT_CONTACT_ID = "contact_id"

class FragmentContactUpdate : Fragment(R.layout.update_fragment) {
    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var numberET: EditText
    private lateinit var updateBtn: Button

    private var updateCallBacks: UpdateCallBacks? = null

    private var contactId: Int = 0
    var contacts = ContactList.contacts

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is UpdateCallBacks)
            updateCallBacks = context
        else
            throw IllegalArgumentException("not implemented to activity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contactId = arguments?.getInt(EDIT_CONTACT_ID) as Int

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameET = view.findViewById(R.id.nameEditText)
        surnameET = view.findViewById(R.id.surnameEditText)
        numberET = view.findViewById(R.id.numberEditText)
        updateBtn = view.findViewById(R.id.update_btn)

        nameET.setText(contacts[contactId].contactName)
        surnameET.setText(contacts[contactId].contactSurname)
        numberET.setText(contacts[contactId].contactNumber)

        updateBtn.setOnClickListener {
            contacts[contactId].apply {
                contactName = nameET.text.toString()
                contactSurname = surnameET.text.toString()
                contactNumber = numberET.text.toString()
            }

            updateCallBacks?.onUpdateContactClickListener(contactId)
        }
    }

    override fun onDetach() {
        super.onDetach()
        updateCallBacks = null
    }

    interface UpdateCallBacks {
        fun onUpdateContactClickListener(contact_id: Int)
    }

    companion object {
        fun newInstance(contact_id: Int): FragmentContactUpdate {
            val args = Bundle().apply {
                putInt(EDIT_CONTACT_ID, contact_id)
            }
            return FragmentContactUpdate().apply {
                arguments = args
            }
        }
    }
}