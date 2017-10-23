package ru.nuts_coon.atlanteam

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import ru.nuts_coon.atlanteam.Application.MyApp
import ru.nuts_coon.atlanteam.realmRepository.*
import javax.inject.Inject


class AppViewModel : ViewModel() {

    @Inject
    lateinit var appModel: AppModel
    var showValuses: Array<Int>

    val postLiveData = MutableLiveData<PostObject>()
    val commentLiveData = MutableLiveData<CommentObject>()
    val photoLiveData = MutableLiveData<String>()
    val todosLiveData = MutableLiveData<TodosObject>()
    val showProgressBar = MutableLiveData<Array<Int>>()
    val showErrorMessage = MutableLiveData<Boolean>()

    init {
        MyApp.component.inject(this)
        showValuses = arrayOf(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE)
    }

    fun getPost(postId: String) {
        appModel.getPost(postId)
                .onErrorReturn { PostAnswer().also { showErrorMessage.value = true } }
                .subscribe {
                    val id = it.id.toString()
                    val title = it.title
                    val body = it.body

                    appModel.getUser(it.userId.toString())
                            .onErrorReturn { UserAnswer().also { showErrorMessage.value = true } }
                            .subscribe {
                                val postObject = PostObject(postId, it.name, id, title, body)
                                showValuses[0] = View.INVISIBLE
                                showProgressBar.value = showValuses
                                postLiveData.value = postObject
                                appModel.savePost(postObject)
                            }
                }
    }



    fun subscribePost(postId: String) {
        val post = appModel.subscribePost()
        if(post != null){
            postLiveData.value = PostObject(postId, post.user, post.postId, post.title, post.body)
            showValuses[0] = View.INVISIBLE
            showProgressBar.value = showValuses
        }else{
            getPost(postId)
        }
    }

    fun getComment(commentId: String){
        appModel.getComment(commentId)
                .onErrorReturn { CommentAnswer().also { showErrorMessage.value = true } }
                .subscribe {
                    val id = it.id
                    val name = it.name
                    val email = it.email
                    val comment = it.body

                    appModel.getPost(it.postId.toString())
                            .onErrorReturn { PostAnswer().also { showErrorMessage.value = true } }
                            .subscribe {
                                val commentObject = CommentObject(it.title, id.toString(), name, email, comment)
                                commentLiveData.value = commentObject
                                appModel.saveComment(commentObject)
                                showValuses[1] = View.INVISIBLE
                                showProgressBar.value = showValuses
                                appModel.saveComment(commentObject)
                            }
                }
    }

    fun subscribeComment(commentId: String){
        val comment = appModel.subscribeComment()
        if (comment != null){
            commentLiveData.value = CommentObject(comment.postTitle, comment.commentId, comment.name, comment.email, comment.comment)
            showValuses[1] = View.INVISIBLE
            showProgressBar.value = showValuses
        }else{
            getComment(commentId)
        }
    }

    fun getPhoto(photoId: String){
        appModel.getPhoto(photoId)
                .onErrorReturn { PhotoAnswer().also { showErrorMessage.value = true } }
                .subscribe {
                    photoLiveData.value = it.url
                    showValuses[2] = View.INVISIBLE
                    showProgressBar.value = showValuses
                }
    }

    fun getTodos(todosId: String){
        appModel.getTodos(todosId)
                .onErrorReturn { TodosAnswer().also { showErrorMessage.value = true } }
                .subscribe {
                    val userId = it.userId
                    val id = it.id
                    val title = it.title
                    val completed = it.isCompleted
                    appModel.getUser(userId.toString())
                            .onErrorReturn { UserAnswer().also { showErrorMessage.value = true } }
                            .subscribe {
                                todosLiveData.value = TodosObject(it.name, id.toString(), title, completed.toString())
                                showValuses[3] = View.INVISIBLE
                                showProgressBar.value = showValuses
                            }

                }
    }
}