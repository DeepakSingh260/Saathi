package com.example.saathi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class splash_screen : AppCompatActivity() {

    private lateinit var customer: Button
    private lateinit var seller: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        customer = findViewById(R.id.customer_btn)
        seller = findViewById(R.id.business_btn)

        customer.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@splash_screen , LogIn::class.java))
            }
        })
        seller.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@splash_screen , LogIn::class.java))
            }
        })
    }
}