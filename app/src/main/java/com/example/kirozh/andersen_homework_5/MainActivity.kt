package com.example.kirozh.andersen_homework_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main),
    FragmentContactDetail.DetailCallBacks,
    FragmentContactList.CallBacks,
    FragmentContactUpdate.UpdateCallBacks {

    private var phoneConfig = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        phoneConfig = resources.getBoolean(R.bool.isPhone)

        if (phoneConfig) {
            createListFragment(R.id.fragment_container)
        } else {
            createListFragment(R.id.left_fragment_container)
            createDetailFragment(R.id.right_fragment_container, 0)
        }
    }

    override fun onTextViewClickListener(id: Int) {
        supportFragmentManager.beginTransaction().run {
            val detailFragment = FragmentContactDetail.newInstance(id)
            if (phoneConfig)
                replace(R.id.fragment_container, detailFragment)
            else
                replace(R.id.right_fragment_container, detailFragment)
            addToBackStack("detail")
            commit()
        }

    }

    override fun onEditContactClickListener(id: Int) {

        supportFragmentManager.beginTransaction().run {
            val editFragment = FragmentContactUpdate.newInstance(id)
            if (phoneConfig)
                replace(R.id.fragment_container, editFragment)
            else
                replace(R.id.right_fragment_container, editFragment)
            addToBackStack("update")
            commit()
        }
    }

    override fun onUpdateContactClickListener(id: Int) {
        if (phoneConfig) {
            createListFragment(R.id.fragment_container)
        } else {
            createListFragment(R.id.left_fragment_container)
            createDetailFragment(R.id.right_fragment_container, id)
        }
    }

    override fun onBackPressed() {
        if (phoneConfig) {
            if (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                    .name.toString() == "list"
            )
                this.finish()
            else
                supportFragmentManager.popBackStackImmediate()
        } else {
            if (supportFragmentManager
                    .getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                    .name.toString() == "detail"
            )
                this.finish()
            else
                supportFragmentManager.popBackStackImmediate()

        }
    }

    private fun createDetailFragment(layoutId: Int, contactId: Int) {
        supportFragmentManager.beginTransaction().run {
            val detailFragment = FragmentContactDetail.newInstance(contactId)
            replace(layoutId, detailFragment)
            addToBackStack("detail")
            commit()
        }
    }

    private fun createListFragment(layoutId: Int) {
        supportFragmentManager.beginTransaction().run {
            val updatedListFragment = FragmentContactList.newInstance()
            replace(layoutId, updatedListFragment)
            addToBackStack("list")
            commit()
        }
    }
}