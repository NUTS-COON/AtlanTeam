package ru.nuts_coon.atlanteam.screen.userScreen

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import ru.nuts_coon.atlanteam.Application.MyApp
import ru.nuts_coon.atlanteam.UserAnswer
import javax.inject.Inject


class UserViewModel : ViewModel() {

    @Inject
    lateinit var userModel: UserModel

    init {
        MyApp.component.inject(this)
    }

    val userLiveData = MutableLiveData<List<UserAnswer>>()
    val showProgressBar = MutableLiveData<Boolean>()
    val showErrorMessage = MutableLiveData<Boolean>()

    fun getUser(count: Int){
        Observable.range(1, count)
                .flatMap { userModel.getUser(it.toString()) }
                .onErrorReturn {
                    UserAnswer().also { showErrorMessage.value = true }
                }
                .buffer(count)
                .subscribe {
                    //Sometimes data comes in random order
                    it.sortBy { it.id }
                    userLiveData.value = it
                    showProgressBar.value = false
                }
    }
}