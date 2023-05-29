package com.example.appsuperheromvvm.ui.superhero.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsuperheromvvm.R
import com.example.appsuperheromvvm.core.BaseViewHolder
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.databinding.HeroItemBinding
import com.example.appsuperheromvvm.ui.SuperHeroViewHolder

class SuperHeroAdapter(private var superHeroList: List<ResultsItemsResponse> = emptyList(),private val onItemSelected:(String)->Unit):RecyclerView.Adapter<SuperHeroViewHolder>() {




    fun updatelist(superHeroList: List<ResultsItemsResponse>){
        this.superHeroList=superHeroList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.hero_item,parent,false))
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superHeroList[position],onItemSelected)
    }

    override fun getItemCount(): Int = superHeroList.size
}