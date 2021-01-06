package com.anuraq.mnote.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anuraq.mnote.R
import com.anuraq.mnote.adapters.MyAdapter
import com.anuraq.mnote.model.Task
import com.anuraq.mnote.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.custom_dialog.view.*
import java.util.*
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val dataObserver = Observer<List<Task>> { Data ->
//            this.NewData = Data
//        }

        val taskViewModel = TaskViewModel(application)

        var newData: List<Task>? = taskViewModel.allTasks.value

        taskViewModel.allTasks.observe(this, { data ->
            newData = data
        })

        Log.d("M - NOTE", newData.toString())

//        var dataSS: LiveData<List<Task>> = taskViewModel.getAll()
//
//        dataSS.observe(this, dataObserver)

//        dataSS = taskViewModel.getAll()

        recyclerView = findViewById<RecyclerView>(R.id.rv).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(application.applicationContext)

            // specify an viewAdapter (see also next example)
            adapter = MyAdapter(newData)

        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val viewgroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView: View = LayoutInflater.from(view.context).inflate(
                R.layout.custom_dialog,
                viewgroup,
                false
            )
            builder.setView(dialogView)

            val spinner: Spinner = dialogView.findViewById(R.id.spinner)
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            dialogView.buttonOk.setOnClickListener(View.OnClickListener {
                val title: String = dialogView.title_edit.text.toString()
                val desc: String = dialogView.desc_edit.text.toString()
                val r: Random = Random()
                taskViewModel.insert(Task(r.nextInt(), title, desc))
                recyclerView.adapter?.notifyDataSetChanged()
                alertDialog.hide()
            })
            //Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null)
            //    .show()
        }
    }
}