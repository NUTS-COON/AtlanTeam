package ru.nuts_coon.atlanteam
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Geo {
    @SerializedName("lat")
    @Expose
    var lat:String = ""
    @SerializedName("lng")
    @Expose
    var lng:String = ""
}