package net.azarquiel.darksky.model

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object Utilidades {

    fun farToCel(far: Double): Double {
        var cel = (far - 32) * 0.5556
        val simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH)
        val df = DecimalFormat("#0.00", simbolos)
        cel = java.lang.Double.valueOf(df.format(cel))
        return cel
    }

    fun cambiarFecha(fecha: Long): String {
        val df = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
        return df.format(fecha * 1000)
    }
}