package ru.nuts_coon.atlanteam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TodosAnswer {

    @SerializedName("userId")
    @Expose
    var userId: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("title")
    @Expose
    var title: String = ""
    @SerializedName("completed")
    @Expose
    var isCompleted: Boolean = false

}