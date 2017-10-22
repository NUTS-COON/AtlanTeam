package ru.nuts_coon.atlanteam.Application

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nuts_coon.atlanteam.*

interface RetrofitAPI {

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @GET("/posts/{id}")
    fun getPost(@Path("id") postId: String): Observable<PostAnswer>

    @GET("/comments/{id}")
    fun getComment(@Path("id") commentId: String): Observable<CommentAnswer>

    @GET("/users/{id}")
    fun getUser(@Path("id") userId: String): Observable<UserAnswer>

    @GET("/photos/{id}")
    fun getPhoto(@Path("id") photoId: String) : Observable<PhotoAnswer>

    @GET("/todos/{id}")
    fun getTodos(@Path("id") todosId: String) : Observable<TodosAnswer>
}