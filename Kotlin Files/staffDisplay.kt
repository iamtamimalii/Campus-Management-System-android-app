package com.example.campusmanagementsyatem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class staffDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_display)

        val progress : ProgressDialog = ProgressDialog(this)
        progress.setMessage("Please Wait Loading...")
        progress.setCanceledOnTouchOutside(false)

        val back: Button = findViewById(R.id.home)
        val view: Button = findViewById(R.id.btn_view)

        val list: ListView = findViewById(R.id.list)
        var myListAdapter : ArrayAdapter<String>?
        val dataList=ArrayList<String>()

        back.setOnClickListener {
            progress.show()
            startActivity(Intent(this, StaffActivity::class.java))
            progress.cancel()
        }

        view.setOnClickListener {
            progress.show()

            val database= Firebase.database
            val db = database.getReference("Management System")

            db.child("Staff").addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (obj in snapshot.children){
                        val temp = obj.getValue<staff>()
                        val studentData ="First Name: ${temp?.fName}\nLast Name: ${temp!!.lName}\nGender: ${temp.gender}\nUniversity: ${temp.uni}"
                        dataList.add(studentData)
                    }
                    myListAdapter=ArrayAdapter<String>(this@staffDisplay, R.layout.customlistview, R.id.text_view_title1, dataList)
                    list.adapter=myListAdapter
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@staffDisplay, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                }
            })
            progress.cancel()
        }
    }
}