package com.jkhome.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NewsAdapter (private val listOfNews: MutableList<Data>) : BaseAdapter(){
    override fun getCount(): Int {
        return listOfNews.count()
    }

    override fun getItem(position: Int): Any {
        return listOfNews[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context = parent?.context
        var rowView = convertView
        val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (rowView == null)
            rowView = inflater.inflate(R.layout.list_item,parent,false)

        val item = listOfNews[position]
        val title = rowView?.findViewById<TextView>(R.id.title_text_view)
        title?.text = item.webTitle
        title?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.webUrl))
            context.startActivity(intent)
        }

        return  rowView!!
    }

}