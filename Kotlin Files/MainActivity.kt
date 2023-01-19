package com.example.campusmanagementsyatem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progress : ProgressDialog = ProgressDialog(this)
        progress.setMessage("Please Wait Loading...")
        progress.setCanceledOnTouchOutside(false)

        val btn1:Button= findViewById(R.id.btn_std)
        val btn2:Button=findViewById(R.id.btn_fac)
        val btn3:Button=findViewById(R.id.btn_staff)

        btn1.setOnClickListener {
            progress.show()
            startActivity(Intent(this, StudentsActivity::class.java))
            progress.cancel()
        }

        btn2.setOnClickListener {
            progress.show()
            startActivity(Intent(this, FacultyActivity::class.java))
            progress.cancel()
        }

        btn3.setOnClickListener {
            progress.show()
            startActivity(Intent(this, StaffActivity::class.java))
            progress.cancel()
        }
    }
}