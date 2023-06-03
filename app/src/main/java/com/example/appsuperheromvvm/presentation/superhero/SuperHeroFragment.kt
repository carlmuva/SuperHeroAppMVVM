package com.example.appsuperheromvvm.presentation.superhero

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.appsuperheromvvm.R
import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.databinding.FragmentSuperHeroBinding
import com.example.appsuperheromvvm.presentation.SuperHeroViewModel
import com.example.appsuperheromvvm.presentation.superhero.adapters.SuperHeroAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SuperHeroFragment : Fragment(), SuperHeroAdapter.OnSuperHeroClickListener {

    private lateinit var mBinding: FragmentSuperHeroBinding

    private val viewModel by activityViewModels<SuperHeroViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        viewModel.fetchMainScreenSuperHeros.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading ->{
                    mBinding.progressBar.visibility = View.VISIBLE

                }
                is Resource.Success->{
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.rvHeros.adapter = SuperHeroAdapter(requireContext(),result.data.results,this)
                    Log.d("LiveData","SuperHero: ${result.data.results}")
                    // Log.d("LiveData", "Upcoming: ${result.data.first} ")
                }
                is Resource.Failure->{
                    mBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("Error","${result.exception}")
                }
            }
        })
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