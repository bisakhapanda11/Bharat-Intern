package com.example.to_do_list
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_card.create_title
import kotlinx.android.synthetic.main.activity_update_card.delete_button
import kotlinx.android.synthetic.main.activity_update_card.update_button

class UpdateCard : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        val pos=intent.getIntExtra("id",-1)
        if (pos!=-1)
        {
            val title=dataobject.getData(pos).title
            val priority = dataobject.getData(pos).priority
            create_title.setText(title)
            create_title.setText(priority)

            delete_button.setOnClickListener {
                dataobject.deleteData(pos)
            }

            update_button.setOnClickListener {
                dataobject.updateData(pos,title,priority)
            }
        }
    }
    fun myIntent(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}