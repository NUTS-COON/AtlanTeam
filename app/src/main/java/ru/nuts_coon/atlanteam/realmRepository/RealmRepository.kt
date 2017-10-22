package ru.nuts_coon.atlanteam.realmRepository

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

class RealmRepository {
    fun updatePost(postObject: PostObject) {
        Realm.getDefaultInstance().use { realm ->
            val realmPost = realm.where(RealmPost::class.java).findFirst()
            if (realmPost != null) {
                realmPost.let { result ->
                    realm.executeTransaction {
                        result.user = postObject.user
                        result.postId = postObject.postId
                        result.title = postObject.title
                        result.body = postObject.body
                    }
                }
            } else {
                realm.executeTransaction {
                    val post = RealmPost()
                    it.copyToRealm(post
                            .apply {
                                this.user = postObject.user
                                postId = postObject.postId
                                this.title = postObject.title
                                body = postObject.body
                            })
                }

            }
        }
    }

    fun subscribePost(): RealmPost? {
        return Realm.getDefaultInstance().where(RealmPost::class.java)
                .findFirst()
    }

    fun updateComment(commentObject: CommentObject){
        Realm.getDefaultInstance().use { realm ->
            val realmComment = realm.where(RealmComment::class.java).findFirst()
            if (realmComment != null){
                realmComment.let { result ->
                    realm.executeTransaction {
                        result.commentId = commentObject.commentId
                        result.postTitle = commentObject.postTitle
                        result.name = commentObject.name
                        result.email = commentObject.email
                        result.comment = commentObject.comment
                    }
                }
            }else{
                realm.executeTransaction {
                    val comment = RealmComment()
                    it.copyToRealm(comment
                            .apply {
                                commentId = commentObject.commentId
                                postTitle = commentObject.postTitle
                                name = commentObject.name
                                email = commentObject.email
                                this.comment = commentObject.comment
                            }
                    )
                }
            }

        }
    }

    fun subscribeComment(): RealmComment? {
        return Realm.getDefaultInstance().where(RealmComment::class.java)
                .findFirst()
    }
}
open class RealmPost : RealmObject(){

    @PrimaryKey
    var _id: String = UUID.randomUUID().toString()
    var user: String = "1"
    var postId = "2"
    var title: String = "3"
    var body: String = "4"
}


open class RealmComment : RealmObject(){

    @PrimaryKey
    var _id: String = UUID.randomUUID().toString()
    var commentId = "6"
    var postTitle = "2"
    var name: String = "3"
    var email: String = "4"
    var comment: String = "5"
}

data class PostObject(val userId: String, val user: String, val postId: String, val title: String, val body: String)
data class CommentObject(val postTitle: String, val commentId: String, val name: String, val email: String, val comment: String)
data class TodosObject(val user: String, val id: String, val title: String, val completed: String)