package ru.nuts_coon.atlanteam

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nuts_coon.atlanteam.Application.MyApp
import ru.nuts_coon.atlanteam.Application.RetrofitAPI
import ru.nuts_coon.atlanteam.realmRepository.*
import javax.inject.Inject


class AppModel{

    @Inject lateinit var repository: RealmRepository
    @Inject lateinit var api: RetrofitAPI

    init {
        MyApp.component.inject(this)
    }


    fun getPost(postId: String): io.reactivex.Observable<PostAnswer> {
        return api.getPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun savePost(postObject: PostObject){
        repository.updatePost(postObject)
    }

    fun subscribePost(): RealmPost? {
        return repository.subscribePost()
    }

    fun getComment(commentId: String): Observable<CommentAnswer> {
        return api.getComment(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveComment(commentObject: CommentObject){
        repository.updateComment(commentObject)
    }

    fun subscribeComment(): RealmComment? {
        return repository.subscribeComment()
    }

    fun getUser(id: String): Observable<UserAnswer> {
        return api.getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPhoto(photoId: String): Observable<PhotoAnswer>{
        return api.getPhoto(photoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTodos(todosId: String): Observable<TodosAnswer> {
        return api.getTodos(todosId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}