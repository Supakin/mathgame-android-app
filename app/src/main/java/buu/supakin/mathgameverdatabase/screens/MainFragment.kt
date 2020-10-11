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
import buu.supakin.mathgameverdatabase.databinding.FragmentMainBinding
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodelfactories.MainViewModelFactory
import buu.supakin.mathgameverdatabase.viewmodels.MainViewModel
import kotlin.system.exitProcess

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        viewModelFactory = MainViewModelFactory(
            Score(
                MainFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
                MainFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.eventNext.observe(viewLifecycleOwner, Observer { eventNext ->
            if (eventNext)  {
                onNext()
                viewModel.onNextComplete()
            }
        })


        binding.mainViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            exitProcess(0)
        }

        return binding.root
    }

    private fun onNext () {
        view?.findNavController()?.navigate(
            MainFragmentDirections.actionMainFragmentToMenuFragment(
                viewModel.score.value?.scoreCorrect ?:0,
                viewModel.score.value?.scoreInCorrect ?:0
            )
        )
    }

}