package com.example.campusmanagementsyatem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StaffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        val progress : ProgressDialog = ProgressDialog(this)
        progress.setMessage("Please Wait Loading...")
        progress.setCanceledOnTouchOutside(false)

        val fName: EditText =findViewById(R.id.staff_FName)
        val lName: EditText =findViewById(R.id.staff_LName)
        val gender: EditText =findViewById(R.id.staff_Gender)
        val uni: EditText =findViewById(R.id.staff_Uni)

        val btnSave: Button =findViewById(R.id.staff_Submit)
        val btnView: Button =findViewById(R.id.staff_View)
        val home: Button =findViewById(R.id.home)

        home.setOnClickListener {
            progress.show()
            startActivity(Intent(this, MainActivity::class.java))
            progress.cancel()
        }

        btnSave.setOnClickListener{
            progress.show()
            val etFName:String=fName.text.toString()
            val etLName:String=lName.text.toString()
            val etGender:String=gender.text.toString()
            val etUni:String=uni.text.toString()

            val myStaff = staff()
            myStaff.initilizeObject(etFName,etLName,etGender,etUni)

            val database = Firebase.database
            val db = database.getReference("Management System")

            db.child("Staff").child("" + System.currentTimeMillis()).setValue(myStaff).addOnCompleteListener (this) {
                    task ->
                if(task.isSuccessful)
                {
                    Toast.makeText(this, "Your Data is Saved Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Your Data is Failed to Store \n" + task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            fName.setText("")
            lName.setText("")
            gender.setText("")
            uni.setText("")

            progress.cancel()
        }
        btnView.setOnClickListener {
            progress.show()
            startActivity(Intent(this, staffDisplay::class.java))
            progress.cancel()
        }
    }
}