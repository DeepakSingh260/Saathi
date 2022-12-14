package com.example.saathi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.main_grid_view)
        val itemsList: MutableList<items> = ArrayList()
        itemsList.add(items(R.drawable.hostel, "Hostel/PG"))
        itemsList.add(items(R.drawable.food, "Mess Food"))
        itemsList.add(items(R.drawable.books, "Rent Book"))
        itemsList.add(items(R.drawable.person, "Profile"))
        val customAdapter = CustomAdapter(this, R.layout.grid_view_card, itemsList)
        gridView.adapter = customAdapter


    }


}

class CustomAdapter(context: Context, resource: Int, objects: List<items>) : ArrayAdapter<items?>(context, resource, objects) {
    private var page_context:Context
    private var items_list: List<items>
    private var custom_layout_id: Int

    init {
        items_list = objects
        custom_layout_id = resource
        page_context = context
    }

    override fun getCount(): Int {
        return items_list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            // getting reference to the main layout and initializing
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.grid_view_card, null)
        }

        // initializing the imageview and textview and setting data
        val imageView = v!!.findViewById<ImageView>(R.id.imageView)
        val textView = v.findViewById<TextView>(R.id.textView)

        // get the item using the  position param
        val item: items = items_list[position]
        imageView.setImageResource(item.getImage_id())
        textView.setText(item.getText())
        if(item.getText()=="Rent Book"){
            v.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val intent = Intent(page_context ,Book_Page::class.java )
                    page_context.startActivity(intent)
                }
            })
        }
        return v
    }
}
class items internal constructor(private var image_id: Int, private var text: String) {
    fun getImage_id(): Int {
        return image_id
    }

    fun setImage_id(image_id: Int) {
        this.image_id = image_id
    }

    fun getText(): String {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun items(img: Int, text: String) {
        image_id = img
        this.text = text
    }
}
