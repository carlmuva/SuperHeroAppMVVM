package com.example.appsuperheromvvm.ui.superherodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsuperheromvvm.R


class SuperHeroDetailFragment : Fragment() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_super_hero_detail, container, false)
    }


}