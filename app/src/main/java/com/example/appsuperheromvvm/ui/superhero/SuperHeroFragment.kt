package com.example.appsuperheromvvm.ui.superhero

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsuperheromvvm.R
import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.data.remote.SuperHeroDataSource
import com.example.appsuperheromvvm.databinding.FragmentSuperHeroBinding
import com.example.appsuperheromvvm.domain.RetrofitClient

import com.example.appsuperheromvvm.domain.SuperHeroRepositoryImpl
import com.example.appsuperheromvvm.domain.WebService
import com.example.appsuperheromvvm.presentation.SuperHeroViewModel
import com.example.appsuperheromvvm.presentation.SuperheroViewModelFactory
import com.example.appsuperheromvvm.ui.superhero.adapters.SuperHeroAdapter
import com.example.appsuperheromvvm.ui.superherodetails.SuperHeroDetailFragment
import com.example.appsuperheromvvm.ui.superherodetails.SuperHeroDetailFragment.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit


class SuperHeroFragment : Fragment() {

    private lateinit var mBinding: FragmentSuperHeroBinding
    private lateinit var adapter: SuperHeroAdapter

    private val viewModel by viewModels<SuperHeroViewModel> {
        SuperheroViewModelFactory(SuperHeroRepositoryImpl(SuperHeroDataSource(RetrofitClient.webService)))
    }

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
                   // adapter = SuperHeroAdapter{superheroId-> navigateToDetail(superheroId)}
                    mBinding.rvHeros.adapter=SuperHeroAdapter(result.data.results, {superheroId -> navigateToDetail(superheroId)})
                    //mBinding.rvHeros.setHasFixedSize(true)
                   // mBinding.rvHeros.adapter = SuperHeroAdapter(requireContext(),result.data.results,this)
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




    private fun navigateToDetail(id:String){

        findNavController().navigate(R.id.superHeroDetailFragment,)
        val intent = Intent(context, SuperHeroDetailFragment::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)

    }




}