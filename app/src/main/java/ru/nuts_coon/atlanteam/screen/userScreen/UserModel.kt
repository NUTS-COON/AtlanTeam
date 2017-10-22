package ru.nuts_coon.atlanteam.screen.userScreen

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nuts_coon.atlanteam.Application.MyApp
import ru.nuts_coon.atlanteam.Application.RetrofitAPI
import ru.nuts_coon.atlanteam.UserAnswer
import javax.inject.Inject


class UserModel {

    @Inject
    lateinit var api: RetrofitAPI

    init {
        MyApp.component.inject(this)
    }

    fun getUser(id: String): Observable<UserAnswer> {
        return api.getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}