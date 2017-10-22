package ru.nuts_coon.atlanteam
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class UserAnswer {
    @SerializedName("id")
    @Expose
    var id:Int = 0
    @SerializedName("name")
    @Expose
    var name:String = ""
    @SerializedName("username")
    @Expose
    var username:String = ""
    @SerializedName("email")
    @Expose
    var email:String = ""
    @SerializedName("address")
    @Expose
    var address:Address = Address()
    @SerializedName("phone")
    @Expose
    var phone:String = ""
    @SerializedName("website")
    @Expose
    lateinit var website:String
    @SerializedName("company")
    @Expose
    lateinit var company:Company
}