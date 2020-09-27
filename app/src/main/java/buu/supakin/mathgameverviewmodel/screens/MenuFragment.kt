package buu.supakin.mathgameverviewmodel.screens

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
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentMenuBinding
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodelfactories.MenuViewModelFactory
import buu.supakin.mathgameverviewmodel.viewmodels.MenuViewModel


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var  viewModelFactory: MenuViewModelFactory
    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_menu, container, false)

        viewModelFactory = MenuViewModelFactory(
            Score(
                MenuFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
                MenuFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(MenuViewModel::class.java)

        viewModel.eventNextToPlusMode.observe(viewLifecycleOwner, Observer { eventNextToPlusMode ->
            if (eventNextToPlusMode) onNextToPlay(1)
        })

        viewModel.eventNextToMinusMode.observe(viewLifecycleOwner, Observer { eventNextToMinusMode ->
            if (eventNextToMinusMode) onNextToPlay(2)
        })

        viewModel.eventNextToMultipliedMode.observe(viewLifecycleOwner, Observer { eventNextToMultipliedMode ->
            if (eventNextToMultipliedMode) onNextToPlay(3)
        })

        viewModel.eventNextToDivideMode.observe(viewLifecycleOwner, Observer { eventNextToDivideMode ->
            if (eventNextToDivideMode) onNextToPlay(4)
        })

        binding.menuViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                MenuFragmentDirections.actionMenuFragmentToMainFragment(
                    viewModel!!.score.value?.scoreCorrect ?:0,
                    viewModel!!.score.value?.scoreInCorrect ?:0
                )
            )
        }

        return binding.root
    }


    private fun onNextToPlay (menu: Int) {
        view?.findNavController()?.navigate(
            MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                viewModel!!.score.value?.scoreCorrect ?:0,
                viewModel!!.score.value?.scoreInCorrect ?:0,
                menu
            )
        )
    }

}