package com.geeks.noteapp12.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.geeks.noteapp12.R
import com.geeks.noteapp12.ui.data.models.ColorModel

class ColorAdapter(private val context: Context, private val colorModels: List<ColorModel>) : BaseAdapter(){
    override fun getCount(): Int = colorModels.size

    override fun getItem(p0: Int): Any = colorModels[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_custom_dropdown, p2, false)
        val item = getItem(p0) as ColorModel
        val icon = view.findViewById<ImageView>(R.id.image)

        icon.setImageResource(item.iconResId)
        return view
    }
}