package net.azarquiel.darksky.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rowtiempo.view.*
import net.azarquiel.darksky.model.Dias
import net.azarquiel.darksky.model.Utilidades

class CustomAdapter(val context: Context,
                    val layout: Int,
                    val dataList: List<Dias>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Dias){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            itemView.tvPrecipitacionRow.text="${dataItem.precipProbability}%"
            itemView.tvFechaRow.text=Utilidades.cambiarFecha(dataItem.time)
            itemView.tvSummaryRow.text=dataItem.summary
            itemView.tvMinRow.text="${Utilidades.farToCel(dataItem.temperatureMin)}º"
            itemView.tvMaxRow.text="${Utilidades.farToCel(dataItem.temperatureMax)}º"
            // Si la foto viene de Internet
            // implementation 'com.squareup.picasso:picasso:2.5.2'
            Picasso.with(context).load("https://darksky.net/images/weather-icons/${dataItem.icon}.png").into(itemView.ivRow)
            itemView.setTag(dataItem)
        }

    }
}