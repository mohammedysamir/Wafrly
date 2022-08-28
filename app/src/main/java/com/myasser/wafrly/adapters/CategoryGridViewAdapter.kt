package com.myasser.wafrly.adapters

import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.myasser.wafrly.R
import com.myasser.wafrly.views.CategoryActivity

class CategoryGridViewAdapter(private val Categories: List<String>) : BaseAdapter() {
    override fun getCount() = Categories.size

    override fun getItem(position: Int) = Categories[position]

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null)
            return LayoutInflater.from(parent?.context)
                .inflate(R.layout.category_card, parent, false)
        convertView.findViewById<TextView>(R.id.cardTitle).text = Categories[position]
        //set random background color
        val backgroundColor = colorsList.random()
        convertView.findViewById<CardView>(R.id.card)
            .setCardBackgroundColor(ColorStateList.valueOf(backgroundColor))
        //hook listener for navigation
        convertView.setOnClickListener {
            convertView.context.startActivity(Intent(convertView.context,
                CategoryActivity::class.java).putExtra("category name", Categories[position]))
        }
        return convertView
    }
}