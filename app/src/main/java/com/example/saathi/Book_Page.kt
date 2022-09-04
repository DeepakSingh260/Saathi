package com.example.saathi

import android.media.Image
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class Book_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)

        val gridView = findViewById<GridView>(R.id.main_grid_view)
        val itemsList: MutableList<book> = ArrayList()
    }
}

data class book(
    var name:String ,
    var img:String ,
    var rating: Rating
)