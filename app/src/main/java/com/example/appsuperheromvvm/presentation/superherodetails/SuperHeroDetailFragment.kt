package com.example.appsuperheromvvm.presentation.superherodetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appsuperheromvvm.data.model.PowerstatsDetailResponse
import com.example.appsuperheromvvm.data.model.ResultsItemsResponse
import com.example.appsuperheromvvm.databinding.FragmentSuperHeroDetailBinding
import com.example.appsuperheromvvm.presentation.SuperHeroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt


@Suppress("DEPRECATION")
@AndroidEntryPoint
class SuperHeroDetailFragment : Fragment() {


    //private val viewModel by activityViewModels<SuperHeroViewModel>()

   // private lateinit var hero:ResultsItemsResponse
    private lateinit var mBinding: FragmentSuperHeroDetailBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
/*        requireArguments().let {
            hero=it.getParcelable<ResultsItemsResponse>("hero")!!
            Log.d("DETALLES_FRAG", "${hero.toString()}")
        }*/
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentSuperHeroDetailBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hero: ResultsItemsResponse? = arguments?.getParcelable<ResultsItemsResponse>("hero")

        hero?.let {
            Log.d("Stats",it.toString())
            createUI(hero)
        }

        mBinding.btnReturn.setOnClickListener {
            //requireActivity().onBackPressed()
            findNavController().popBackStack()
        }

    }




    private fun createUI(superHeroResult: ResultsItemsResponse){
        Glide.with(requireContext()).load(superHeroResult.image.url).centerCrop().into(mBinding.ivSuperhero)
        mBinding.tvSuperheroName.text = superHeroResult.name
        prepareStats(superHeroResult.powerstats)
        mBinding.tvSuperheroRealName.text = superHeroResult.biography.fullName
        mBinding.tvPublisher.text=superHeroResult.biography.publisher

    }



    private fun prepareStats(powerstats: PowerstatsDetailResponse) {
        /*binding.viewCombat
        val params = binding.viewCombat.layoutParams
        params.height = powerstats.combat.toInt()
        binding.viewCombat.layoutParams=params*/

        updateHeight(mBinding.viewCombat, powerstats.combat)
        updateHeight(mBinding.viewDurability, powerstats.durability)
        updateHeight(mBinding.viewSpeed, powerstats.speed)
        updateHeight(mBinding.viewStrength, powerstats.strength)
        updateHeight(mBinding.viewIntelligence, powerstats.intelligence)
        updateHeight(mBinding.viewPower, powerstats.power)

    }
    private fun updateHeight(view: View, stat: String?) {
        var aux : Float = 0f
        if (stat != null) {
            if (!((stat == "null") || stat.isEmpty())){
                aux=stat.toFloat()
            }

        }

        val params = view.layoutParams
        params.height = pxToDp(aux)
        view.layoutParams = params


    }
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()

    }




}