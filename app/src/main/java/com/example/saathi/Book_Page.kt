package com.example.saathi

import android.content.Context
import android.media.Image
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class Book_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)

        val gridView = findViewById<GridView>(R.id.book_grid_view)
        val itemsList: MutableList<book> = ArrayList()
        val customAdapter = BookAdapter(this, R.layout.book_card, itemsList)
        gridView.adapter = customAdapter

        FirebaseDatabase.getInstance().getReference("Books/").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (shot in snapshot.children.iterator()){
                    val name = shot.child("Name").value.toString()
                    val img = shot.child("Img").value.toString()
                    val address = shot.child("Address").value.toString()
                    val rating = shot.child("Rating").value.toString()

                itemsList.add(book(name,img,rating,address))
                }
                customAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}

class BookAdapter(context: Context, resource: Int, objects: List<book>) : ArrayAdapter<book?>(context, resource, objects) {

    private var items_list: List<book>
    private var custom_layout_id: Int

    init {
        items_list = objects
        custom_layout_id = resource
    }

    override fun getCount(): Int {
        return items_list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            // getting reference to the main layout and initializing
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.book_card, null)
        }

        // initializing the imageview and textview and setting data
        val book_img = v!!.findViewById<ImageView>(R.id.book_img)
        val name = v.findViewById<TextView>(R.id.book_heading)
        val address = v.findViewById<TextView>(R.id.book_address)
        val rating  = v.findViewById<TextView>(R.id.book_rating)
        // get the item using the  position param
        val item: book = items_list[position]
        Picasso.get().load(item.img).into(book_img)
        name.setText(item.name)
        address.setText(item.address)
        rating.setText(item.rating)
        return v
    }


}

data class book(
    var name:String ,
    var img:String ,
    var rating: String,
    var address:String
)