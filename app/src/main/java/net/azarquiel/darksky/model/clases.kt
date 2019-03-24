package net.azarquiel.darksky.model

data class Result(var currently:Currently,var daily:Daily)
data class Currently(var summary:String,var icon:String, var precipProbability:Double, var temperature:Double)
data class Daily(var data:List<Dias>)
data class Dias(var time:Long,var summary: String, var icon: String, var precipProbability: Double, var temperatureMin:Double, var temperatureMax:Double)