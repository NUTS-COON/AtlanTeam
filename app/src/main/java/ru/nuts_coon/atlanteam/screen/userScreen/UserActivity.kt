package ru.nuts_coon.atlanteam.screen.userScreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.users_list.*
import org.jetbrains.anko.toast
import ru.nuts_coon.atlanteam.R
import ru.nuts_coon.atlanteam.UserAnswer


class UserActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_list)
        setupViewModel()
        inflateUser(5)
        setupRecycleView(emptyList())
    }

    private fun setupViewModel(){
        userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel::class.java)
                .apply {
                    userLiveData.observe(this@UserActivity, Observer {
                        user_recyclerview.adapter = UserAdapter(it!!).also { it.notifyDataSetChanged() }
                    })
                    showProgressBar.observe(this@UserActivity, Observer {
                        if (!it!!) usersList_progressbar.visibility = View.INVISIBLE
                    })
                    showErrorMessage.observe(this@UserActivity, Observer {
                        if (it!!) showErrorMessage()
                    })
                }
    }

    private fun inflateUser(count: Int){
        userViewModel.getUser(count)
    }

    private fun showErrorMessage(){
        toast("Failed to get data")
    }

    private fun setupRecycleView(list: List<UserAnswer>){
        user_recyclerview.adapter = UserAdapter(list)
    }
}