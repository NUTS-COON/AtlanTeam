package ru.nuts_coon.atlanteam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentAnswer {

    @SerializedName("postId")
    @Expose
    var postId: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("email")
    @Expose
    var email: String = ""
    @SerializedName("body")
    @Expose
    var body: String = ""

}