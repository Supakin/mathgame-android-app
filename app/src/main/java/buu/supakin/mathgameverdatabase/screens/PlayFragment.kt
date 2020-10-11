package buu.supakin.mathgameverdatabase.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.database.PlayerDatabase
import buu.supakin.mathgameverdatabase.databinding.FragmentPlayBinding
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodelfactories.PlayViewModelFactory
import buu.supakin.mathgameverdatabase.viewmodels.PlayViewModel


class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    private lateinit var viewModel: PlayViewModel
    private lateinit var  viewModelFactory: PlayViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        viewModelFactory = PlayViewModelFactory(
            dataSource,
            PlayFragmentArgs.fromBundle(requireArguments()).playerId,
            PlayFragmentArgs.fromBundle(requireArguments()).menu
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayViewModel::class.java)

        viewModel.eventNext.observe(viewLifecycleOwner, Observer { eventNext->
            if (eventNext) {
                onNext()
                viewModel.onNextComplete()
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                PlayFragmentDirections.actionPlayFragmentToMenuFragment(viewModel.player.value!!.getPlayerId())
            )
        }

        binding.playViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun onNext () {
        val result : Boolean  = viewModel!!.getResult()
        view?.findNavController()?.navigate(
            PlayFragmentDirections.actionPlayFragmentToResultFragment(
                viewModel.player.value!!.getPlayerId(),
                viewModel.menu.value!!,
                result
            )
        )
    }


}