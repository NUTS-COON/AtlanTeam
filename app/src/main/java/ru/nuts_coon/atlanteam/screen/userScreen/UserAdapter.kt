package ru.nuts_coon.atlanteam.screen.userScreen

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_user.view.*
import ru.nuts_coon.atlanteam.Application.inflate
import ru.nuts_coon.atlanteam.R
import ru.nuts_coon.atlanteam.UserAnswer

class UserAdapter(var usersList: List<UserAnswer>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(usersList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.card_user))

    override fun getItemCount(): Int = usersList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(user: UserAnswer){
            with(itemView){
                val address = arrayOf(user.address.city, user.address.street, user.address.suite).joinToString(", ")
                user_id.text = user.id.toString()
                user_name.text = user.name
                user_username.text = user.username
                user_email.text = user.email
                user_phone.text = user.phone
                user_address.text = address
            }
        }
    }

}