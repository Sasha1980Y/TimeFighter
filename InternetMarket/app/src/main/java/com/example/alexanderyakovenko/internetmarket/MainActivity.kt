package com.example.alexanderyakovenko.internetmarket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize a list of string values
        val colors = listOf<String>("Red","Green","Yellow","Blue","Magenta","Pink","White","Gray")

        // Initialize a new array adapter instance
        val adapter = ArrayAdapter(
                this, // Context
                android.R.layout.simple_list_item_1, // Layout
                colors // List
        )

        // Set the grid view adapter/data source
        grid_view.adapter = adapter

        // Set an item click listener for grid view items
        grid_view.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Get the GridView selected/clicked item text
                val selectedItem = parent.getItemAtPosition(position).toString()

                // Display the selected/clicked item text and position on TextView
                text_view.text = "GridView item clicked : $selectedItem \nAt index position : $position"
            }
        }


    }
}
