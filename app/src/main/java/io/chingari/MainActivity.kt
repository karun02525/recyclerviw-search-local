package io.chingari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val myAdapter by lazy { UserAdapter() }
    val list = mutableListOf<UserModel>()


    interface OnClickListener {
        fun onClickEvent(user: UserModel)
    }

    private val listener = object : OnClickListener {
        override fun onClickEvent(user: UserModel) {
            Toast.makeText(applicationContext, user.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        list.run {
            add(UserModel("Karun Kumar"))
            add(UserModel("Mukesh Singh"))
            add(UserModel("Sita Devi"))
            add(UserModel("Ram Singh"))
            add(UserModel("Vikash Singh"))
            add(UserModel("Ankit Mistra"))
            add(UserModel("Ompraksh Singh"))
            add(UserModel("Tark Mehta"))
        }

        myAdapter.list = list
        myAdapter.setListener(listener)
        rv_contacts.adapter = myAdapter
        myAdapter.notifyDataSetChanged()

        searchOperation()
    }

    private fun searchOperation() {
        search_suggest.requestFocus()
        search_suggest.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })

        search_suggest.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<UserModel>()
        list.filterTo(filteredNames) {
            it.name.toLowerCase().contains(text.toLowerCase())
        }

        if(filteredNames.isEmpty()){
            no_item.visibility=View.VISIBLE
        }else{
            no_item.visibility=View.GONE
        }
        myAdapter.filterList(filteredNames)
    }


}