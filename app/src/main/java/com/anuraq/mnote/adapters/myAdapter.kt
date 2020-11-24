package com.anuraq.mnote.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.anuraq.mnote.MyOnClickListener
import com.anuraq.mnote.R
import com.anuraq.mnote.model.Task
import kotlinx.android.synthetic.main.my_text_view.view.*

class MyAdapter(private val myDataset: List<Task>?) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false) as RelativeLayout
        val mOnClickListener: View.OnClickListener = MyOnClickListener(parent.context, layout)
        layout.notify.setOnClickListener(mOnClickListener)
        layout.done.setOnClickListener(mOnClickListener)

        // set the view's size, margins, paddings and layout parameters
        //...
        return MyViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.layout.title.text = myDataset?.get(position)?.title.toString()
        holder.layout.info.text = myDataset?.get(position)?.desc.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        if(myDataset == null)
            return 0
        else
            return myDataset.size
    }
}