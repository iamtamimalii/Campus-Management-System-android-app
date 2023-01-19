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

class FacultyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty)

        val progress : ProgressDialog = ProgressDialog(this)
        progress.setMessage("Please Wait Loading...")
        progress.setCanceledOnTouchOutside(false)

        val fName: EditText =findViewById(R.id.fac_FName)
        val lName: EditText =findViewById(R.id.fac_LName)
        val gender: EditText =findViewById(R.id.fac_Gender)
        val reg: EditText =findViewById(R.id.fac_Reg)
        val uni: EditText =findViewById(R.id.fac_Uni)
        val fac: EditText =findViewById(R.id.fac_fac)

        val btnSave: Button =findViewById(R.id.fac_Submit)
        val btnView: Button =findViewById(R.id.fac_View)
        val home: Button =findViewById(R.id.home)

        home.setOnClickListener {
            progress.show()
            startActivity(Intent(this, MainActivity::class.java))
            progress.cancel()
        }

        btnSave.setOnClickListener {
            progress.show()

            val etFName: String = fName.text.toString()
            val etLName: String = lName.text.toString()
            val etGender: String = gender.text.toString()
            val etReg: String = reg.text.toString()
            val etUni: String = uni.text.toString()
            val etFac: String = fac.text.toString()

            val myFaculty = faculty()
            myFaculty.initilizeObject(etFName,etLName,etGender,etReg,etUni,etFac)

            val database = Firebase.database
            val db = database.getReference("Management System")

            db.child("Faculty").child("" + System.currentTimeMillis()).setValue(myFaculty).addOnCompleteListener (this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Your Data is Saved Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this,
                        "Your Data is Failed to Store \n" + task.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            fName.setText("")
            lName.setText("")
            gender.setText("")
            reg.setText("")
            uni.setText("")
            fac.setText("")

            progress.cancel()
        }
        btnView.setOnClickListener {
            progress.show()
            startActivity(Intent(this, facultyDisplay::class.java))
            progress.cancel()
        }
    }
}