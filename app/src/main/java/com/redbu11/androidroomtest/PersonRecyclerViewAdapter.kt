package com.redbu11.androidroomtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.redbu11.androidroomtest.databinding.ListItemBinding
import com.redbu11.androidroomtest.db.Person

class PersonRecyclerViewAdapter(private val clickListener:(Person)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val personsList = ArrayList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return personsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(personsList[position],clickListener)
    }

    fun setList(subscribers: List<Person>) {
        personsList.clear()
        personsList.addAll(subscribers)

    }

}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber: Person,clickListener:(Person)->Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.contentContainer.setOnClickListener{
            clickListener(subscriber)
        }
    }
}
