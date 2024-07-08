package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.add
import kotlinx.android.synthetic.main.activity_main.deleteAll
import kotlinx.android.synthetic.main.activity_main.recycler_view

class MainActivity : AppCompatActivity() {
    private lateinit var add:Button
    private lateinit var deleteAll:Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        add.setOnClickListener{
            val intent=Intent(this,CreateCard::class.java)
            startActivity(intent)
        }
        deleteAll.setOnClickListener {
            dataobject.deleteAll()

        }

        recycler_view.adapter=Adapter(dataobject.getAllData())
        recycler_view.layoutManager=LinearLayoutManager(this)
    }
}