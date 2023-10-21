package com.gl4.tp2

import Student
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import studentListAdapter
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    val recyclerView : RecyclerView by lazy { findViewById(R.id.recyclerview) }
    val search : EditText by lazy { findViewById(R.id.editText) }
    val presence : RadioGroup by lazy { findViewById(R.id.ratioGroup) }

    var studentList = arrayListOf<Student>(
        Student("Mourali","Sandra","F"),
        Student("Mankai","Nada","F"),
        Student("Dhouib","Mohamed","H"),
        Student("Hamidou","Nessma","F"),
        Student("Doss","Chiraz","F"),
        Student("Zairi","Maissene","H"),
        Student("Ridene","Eya","F"),
        Student("Haddad","Hani","H"),

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = studentListAdapter(studentList,"",presence)

        /* Presence button */
        presence.setOnCheckedChangeListener { radioGroup, i ->
            run {
                val selectedId = radioGroup.checkedRadioButtonId
                val radioButton = findViewById<RadioButton>(selectedId)
                recyclerView.adapter = studentListAdapter(studentList,radioButton.text.toString(),presence)
            }}

            /* RecyclerView */
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                val dividerItemDecoration = DividerItemDecoration(
                    recyclerView.context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }

            search.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    Log.d("filter", "beforeTextChanged");
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    (recyclerView.adapter as studentListAdapter).filter.filter(s)
                }

                override fun afterTextChanged(p0: Editable?) {
                    Log.d("filter", "afterTextChanged");
                }

            })


        }
    }
