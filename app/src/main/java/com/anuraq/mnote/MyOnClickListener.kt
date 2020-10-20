package com.anuraq.mnote

import android.R
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.my_text_view.view.*


class MyOnClickListener(private val cont: Context, val RL: RelativeLayout) : View.OnClickListener {
    override fun onClick(view: View?) {

        when (view!!.id) {
            RL.notify.id -> Toast.makeText(cont, "Hello Javatpoint"+ RL.title.text, Toast.LENGTH_SHORT).show()
            RL.done.id -> Toast.makeText(cont, "Hello Javatpoint" + RL.title.text, Toast.LENGTH_SHORT).show()
        }
    }

}