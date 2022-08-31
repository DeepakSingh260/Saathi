package com.example.saathi

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import android.R.attr.password
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignIn : AppCompatActivity() {

    private lateinit var signInEmail:EditText
    private lateinit var signInPassword:EditText
    private lateinit var SignInBtn:Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInEmail = findViewById(R.id.sign_email)
        signInPassword = findViewById(R.id.sign_password)

        SignInBtn = findViewById(R.id.btn_signin)
        auth = Firebase.auth


        SignInBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val email = signInEmail.text.toString().trim()
                val password = signInPassword.text.toString().trim()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@SignIn) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
            }
        })
    }

    fun updateUI(user: FirebaseUser?){
        if(user!=null){
            startActivity(Intent(this@SignIn , MainActivity::class.java))
        }
    }

    private val TAG = "SignIn Activity"
}