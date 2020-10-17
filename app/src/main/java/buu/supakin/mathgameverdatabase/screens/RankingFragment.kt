package buu.supakin.mathgameverdatabase.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.adapter.RankingAdapter
import buu.supakin.mathgameverdatabase.database.PlayerDatabase
import buu.supakin.mathgameverdatabase.databinding.FragmentRankingBinding
import buu.supakin.mathgameverdatabase.viewmodelfactories.RankingViewModelFactory
import buu.supakin.mathgameverdatabase.viewmodels.RankingViewModel



class RankingFragment : Fragment() {
    private lateinit var binding: FragmentRankingBinding
    private lateinit var viewModel: RankingViewModel
    private lateinit var  viewModelFactory: RankingViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ranking, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        viewModelFactory = RankingViewModelFactory(
            dataSource,
            application
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(RankingViewModel::class.java)

        val adapter = RankingAdapter()

        binding.rankingList.adapter = adapter
        binding.rankingViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.playerList.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.i("Ranking", it[0].name)
                adapter.data = it
            }
        })



        return binding.root
    }


}