package com.example.saathi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {

    private lateinit var if_new:TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var logInEmail:EditText
    private lateinit var logInPassword:EditText
    private lateinit var logInBtn:Button
    private val TAG = "LogIn Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        if_new = findViewById(R.id.sign_in)
        auth = Firebase.auth
        logInEmail = findViewById(R.id.login_email)
        logInPassword = findViewById(R.id.login_password)
        logInBtn = findViewById(R.id.btn_login)

        if_new.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@LogIn , SignIn::class.java))
            }
        })

        logInBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val email:String = logInEmail.text.toString().trim()
                val password:String = logInPassword.text.toString().trim()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@LogIn) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
            }
        })

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
           startActivity(Intent(this@LogIn , MainActivity::class.java))
            finish()
        }
    }

    fun updateUI(user: FirebaseUser?){
        if(user!=null){
            startActivity(Intent(this@LogIn , MainActivity::class.java))
            finish()
        }
    }

}