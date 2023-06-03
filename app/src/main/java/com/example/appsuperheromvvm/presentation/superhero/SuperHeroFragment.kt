package com.example.appsuperheromvvm.presentation.superhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.appsuperheromvvm.R
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.databinding.FragmentSuperHeroBinding
import com.example.appsuperheromvvm.presentation.SuperHeroViewModel
import com.example.appsuperheromvvm.presentation.superhero.adapters.SuperHeroAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SuperHeroFragment : Fragment(), SuperHeroAdapter.OnSuperHeroClickListener {

    private lateinit var mBinding: FragmentSuperHeroBinding

    private val viewModel : SuperHeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentSuperHeroBinding.inflate(inflater,container,false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupSearchView()
        setupObservers()


    }

    private fun setupObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{
                    println(it.toString())
                    mBinding.progressBar.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    it.superHero?.let { superHero ->
                        println(superHero.toString() + "isiiiiiiiiiiiiiiiiiiiiiiiiiiiii")
                        mBinding.rvHeros.adapter = SuperHeroAdapter(requireContext(), superHero.results,this@SuperHeroFragment)
                    }
                }
            }
        }
    }

    private fun setupSearchView(){
        mBinding.searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSuperHeroName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {return false}
        })
    }



   override fun onSuperHeroClick(superHero: ResultsItemsResponse) {
        val  bundle = Bundle()
        bundle.putParcelable("hero",superHero)
       findNavController().navigate(R.id.action_superHeroFragment_to_superHeroDetailFragment,bundle)
    }
}