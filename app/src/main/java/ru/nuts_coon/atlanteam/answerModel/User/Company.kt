package ru.nuts_coon.atlanteam
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Company {
    @SerializedName("name")
    @Expose
    var name:String = ""
    @SerializedName("catchPhrase")
    @Expose
    var catchPhrase:String = ""
    @SerializedName("bs")
    @Expose
    var bs:String = ""
}