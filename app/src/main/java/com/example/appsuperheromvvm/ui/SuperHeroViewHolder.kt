package com.example.appsuperheromvvm.ui

import android.view.View
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.databinding.HeroItemBinding

class SuperHeroViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val mBinding = HeroItemBinding.bind(view)

    fun bind(resultsItemsResponse: ResultsItemsResponse,onItemSelected:(String)->Unit){
        mBinding.tvSuperheroName.text=resultsItemsResponse.name
        Glide.with(mBinding.ivSuperhero.context)
            .load(resultsItemsResponse.image.url)
            .centerCrop()
            .into(mBinding.ivSuperhero)
        mBinding.root.setOnClickListener { onItemSelected(resultsItemsResponse.id)  }
    }

}