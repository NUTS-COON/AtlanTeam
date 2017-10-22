package ru.nuts_coon.atlanteam.screen

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import org.jetbrains.anko.support.v4.browse
import org.jetbrains.anko.support.v4.email
import ru.nuts_coon.atlanteam.R

class ContactsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_contacts, container, false)
        setupClickListener(view)

        return view
    }

    private fun setupClickListener(view: View){
        view.github.setOnClickListener {
            browse("https://github.com/nuts-coon")
        }
        view.gmail.setOnClickListener {
            email("mburavczov@gmail.com", "", "")
        }
        view.vk.setOnClickListener {
            browse("https://vk.com/id325953138")
        }
        view.whatsapp.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "I like your app")
            intent.`package` = "com.whatsapp"
            startActivity(intent)
        }
    }
}