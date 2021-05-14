package com.example.bookapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.editTextTextEmailAddress
import kotlinx.android.synthetic.main.activity_main.editTextTextPassword
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check if already auth
        if(firebaseRepo.getUser() != null){
            val intent = Intent(this, ListBookActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(view: View){
        // Verify inputs
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()
        if (email.isEmpty() || email == ""){
            Toast.makeText(this,"L'email est vide",Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty() || password == ""){
            Toast.makeText(this,"Le mot de passe est vide",Toast.LENGTH_SHORT).show()
        } else {
            // Attempt connection
            firebaseRepo.signInUser(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, ListBookActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Mauvais email / mot de passe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}