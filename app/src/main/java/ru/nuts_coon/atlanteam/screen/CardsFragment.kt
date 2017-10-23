package ru.nuts_coon.atlanteam.screen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_comment.*
import kotlinx.android.synthetic.main.card_comment.view.*
import kotlinx.android.synthetic.main.card_photo.*
import kotlinx.android.synthetic.main.card_post.*
import kotlinx.android.synthetic.main.card_post.view.*
import kotlinx.android.synthetic.main.card_todos.*
import kotlinx.android.synthetic.main.fragment_cards.view.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import ru.nuts_coon.atlanteam.AppViewModel
import ru.nuts_coon.atlanteam.R
import ru.nuts_coon.atlanteam.screen.userScreen.UserActivity
import java.util.*


class CardsFragment : Fragment() {

    lateinit var appViewModel: AppViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_cards, container, false)
        setupViewModel()
        inflatePostCard(view)
        inflateComment(view)
        inflatePhoto()
        inflateTodos()
        setupPostClickListener(view)
        setupCommentClickListener(view)
        setupUsersClickListener(view)
        setupEditListener(view)

        return view
    }

    private fun setupViewModel(){
        appViewModel = ViewModelProviders
                .of(this)
                .get(AppViewModel::class.java)
                .apply {
                    postLiveData.observe(this@CardsFragment, Observer {
                        tv_post_number.text = it!!.postId
                        et_post_number.setText(it.postId)
                        post_user.text = it.user
                        title.text = it.title
                        body.text = it.body.replace("\n", "/n")

                        edit_post.visibility = View.VISIBLE
                        tv_post_number.visibility = View.VISIBLE
                        done_post.visibility = View.INVISIBLE
                        et_post_number.visibility = View.INVISIBLE
                    })
                    commentLiveData.observe(this@CardsFragment, Observer {
                        tv_comment_number.text = it!!.commentId
                        et_comment_number.setText(it.commentId)
                        post_title.text = it.postTitle
                        comment_name.text = it.name
                        comment_email.text = it.email
                        comment.text = it.comment.replace("\n", "/n")

                        edit_comment.visibility = View.VISIBLE
                        tv_comment_number.visibility = View.VISIBLE
                        done_comment.visibility = View.INVISIBLE
                        et_comment_number.visibility = View.INVISIBLE
                    })
                    photoLiveData.observe(this@CardsFragment, Observer {
                        Picasso.with(context)
                                .load(it)
                                .error(R.drawable.oops)
                                .into(imageView)
                    })
                    todosLiveData.observe(this@CardsFragment, Observer {
                        todos_number.text = it!!.id
                        todos_user.text = it.user
                        todos_title.text = it.title
                        competed.text = it.completed
                    })
                    showProgressBar.observe(this@CardsFragment, Observer {
                        progress_bar_post.visibility = it!![0]
                        progress_bar_comment.visibility = it[1]
                        progress_bar_photo.visibility = it[2]
                        progress_bar_todos.visibility = it[3]
                    })
                    showErrorMessage.observe(this@CardsFragment, Observer {
                        if (it!!) {
                            showErrorMessage()
                        }
                    })
                }
    }

    private fun inflatePostCard(view: View) {
        appViewModel.subscribePost(view.tv_post_number.text.toString())
    }

    private fun inflateComment(view: View){
        appViewModel.subscribeComment(view.tv_comment_number.text.toString())
    }

    private fun inflatePhoto(){
        appViewModel.getPhoto("3")
    }

    private fun inflateTodos(){
        val random = Random(System.currentTimeMillis()).nextInt(199) + 1
        appViewModel.getTodos(random.toString())

    }

    private fun showErrorMessage(){
        toast("Failed to get data")
    }

    private fun setupPostClickListener(view: View){
        view.edit_post.setOnClickListener {
            edit_post.visibility = View.INVISIBLE
            tv_post_number.visibility = View.INVISIBLE
            done_post.visibility = View.VISIBLE
            et_post_number.visibility = View.VISIBLE
        }

        view.done_post.setOnClickListener {
            if (et_post_number.text.isNullOrEmpty()) et_post_number.setText(1.toString())

            progress_bar_post.visibility = View.VISIBLE
            tv_post_number.text = et_post_number.text.toString()
            appViewModel.getPost(et_post_number.text.toString())
        }
    }

    private fun setupCommentClickListener(view: View){
        view.edit_comment.setOnClickListener {
            edit_comment.visibility = View.INVISIBLE
            tv_comment_number.visibility = View.INVISIBLE
            done_comment.visibility = View.VISIBLE
            et_comment_number.visibility = View.VISIBLE
        }

        view.done_comment.setOnClickListener {
            if (et_comment_number.text.isNullOrEmpty()) et_comment_number.setText(1.toString())

            progress_bar_comment.visibility = View.VISIBLE
            tv_comment_number.text = et_comment_number.text.toString()
            appViewModel.getComment(et_comment_number.text.toString())
        }
    }

    private fun setupUsersClickListener(view: View){
        view.user.setOnClickListener {
            startActivity<UserActivity>()
        }
    }

    private fun setupEditListener(view: View){
        view.et_post_number.textChangedListener {
            afterTextChanged { s ->
                if(!s.isNullOrEmpty()) {
                    if (s.toString().toInt() < 1) et_post_number.setText(1.toString())
                    if (s.toString().toInt() > 100) et_post_number.setText(100.toString())
                }
            }
        }

        view.et_comment_number.textChangedListener {
            afterTextChanged { s ->
                if(!s.isNullOrEmpty()) {
                    if (s.toString().toInt() < 1) et_comment_number.setText(1.toString())
                    if (s.toString().toInt() > 500) et_comment_number.setText(500.toString())
                }
            }
        }
    }
}