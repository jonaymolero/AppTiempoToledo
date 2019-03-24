package net.azarquiel.darksky.view

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.darksky.R
import net.azarquiel.darksky.model.Result
import net.azarquiel.darksky.model.Utilidades
import net.azarquiel.recyclerclase.adapter.CustomAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.uiThread
import java.net.URL

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var result:Result
    private lateinit var p: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        p=indeterminateProgressDialog("Cargando DarkSky")
        p.show()
        loadJson()
    }

    private fun loadJson() {
        doAsync {
            var json=URL("https://api.darksky.net/forecast/21259f9de3537b4f719c53580fa39c3a/39.8710026,-4.0251675?lang=es&exclude=minutely,hourly,alerts,flags").readText()
            result=Gson().fromJson(json,Result::class.java)
            uiThread {
                p.hide()
                pintar()
            }
        }
    }

    private fun pintar(){
        Picasso.with(this).load("https://darksky.net/images/weather-icons/${result.currently.icon}.png").into(ivMain)
        tvCurrentlyMain.text=result.currently.summary
        tvTemperaturaMain.text="${Utilidades.farToCel(result.currently.temperature)}º"
        tvPrecipitacionMain.text="${result.currently.precipProbability}%"
        var adapter=CustomAdapter(this,R.layout.rowtiempo,result.daily.data)
        rvTiempo.layoutManager=LinearLayoutManager(this)
        rvTiempo.adapter=adapter
    }
}
