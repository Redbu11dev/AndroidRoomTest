package com.redbu11.androidroomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbu11.androidroomtest.databinding.ActivityMainBinding
import com.redbu11.androidroomtest.db.Person
import com.redbu11.androidroomtest.db.PersonDatabase
import com.redbu11.androidroomtest.db.PersonRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var personViewModel: PersonViewModel
    private lateinit var adapter: PersonRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = PersonDatabase.getInstance(application).personDAO
        val repository = PersonRepository(dao)
        val factory = PersonViewModelFactory(repository, application)
        personViewModel = ViewModelProvider(this,factory).get(PersonViewModel::class.java)
        binding.myViewModel = personViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        personViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonRecyclerViewAdapter {selectedItem:Person->listItemClicked(selectedItem)}
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        personViewModel.persons.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(person: Person){
        personViewModel.initUpdateAndDelete(person)
    }
}
