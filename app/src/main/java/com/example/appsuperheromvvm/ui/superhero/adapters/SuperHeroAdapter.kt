package com.example.appsuperheromvvm.ui.superhero.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsuperheromvvm.R
import com.example.appsuperheromvvm.core.BaseViewHolder
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.databinding.HeroItemBinding

class SuperHeroAdapter(private val context: Context, private val superHeroList: List<ResultsItemsResponse>,private val itemClickListener: OnSuperHeroClickListener):RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnSuperHeroClickListener{
        fun onSuperHeroClick(superHero: SuperHero)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
      return MainViewHolder(
          LayoutInflater.from(context).inflate(R.layout.hero_item,parent,false)
      )
    }


    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int
    ) {
        when(holder){
            is MainViewHolder ->holder.bind(superHeroList[position],position)
        }
    }

    override fun getItemCount(): Int = superHeroList.size


    inner class MainViewHolder(itemView: View): BaseViewHolder<ResultsItemsResponse>(itemView){
        val mBinding = HeroItemBinding.bind(itemView)

        override fun bind(item: ResultsItemsResponse, position: Int) {
            Glide.with(mBinding.ivSuperhero.context)
                .load(item.image.url)
                .centerCrop()
                .into(mBinding.ivSuperhero)
            mBinding.tvSuperheroName.text=item.name
        }


    }





}