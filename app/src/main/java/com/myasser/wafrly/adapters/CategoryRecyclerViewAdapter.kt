package com.myasser.wafrly.adapters

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Category
import com.myasser.wafrly.models.database.AccountOperators
import com.myasser.wafrly.models.database.FireAccountOperator
import com.myasser.wafrly.views.CategoryActivity

class CategoryRecyclerViewAdapter(private val categories: List<Category>, context: Context) :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {
    private val accountRepo: AccountOperators = FireAccountOperator(context)

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryTextView: TextView = view.findViewById(R.id.cardTitle)
        val cardView: CardView = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        //todo: test listener
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        val categoryName = view.findViewById<android.widget.TextView>(R.id.cardTitle)
        view.setOnClickListener {
            view.context.startActivity(Intent(view.context,
                CategoryActivity::class.java).putExtra("category name", categoryName.text))
        }
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        //assign card color and text color
        //todo: test color changes for background and text
        var textColor = R.color.black
        val backgroundColor = colorsList.random()
        if (backgroundColor != Color.parseColor("#FFCB77")) { //yellow background must have black text
            textColor = R.color.white
        }
        holder.categoryTextView.text = category.name
        holder.categoryTextView.setTextColor(ColorStateList.valueOf(holder.itemView.context.resources.getColor(
            textColor,
            null)))
        holder.cardView.setCardBackgroundColor(ColorStateList.valueOf(backgroundColor))
    }

    override fun getItemCount() = categories.size
}