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
import buu.supakin.mathgameverdatabase.databinding.FragmentMenuBinding
import buu.supakin.mathgameverdatabase.viewmodelfactories.MenuViewModelFactory
import buu.supakin.mathgameverdatabase.viewmodels.MenuViewModel


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
        val application = requireNotNull(this.activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        viewModelFactory = MenuViewModelFactory(
            dataSource,
            MenuFragmentArgs.fromBundle(requireArguments()).playerId
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(MenuViewModel::class.java)

        viewModel.eventNextToPlusMode.observe(viewLifecycleOwner, Observer { eventNextToPlusMode ->
            if (eventNextToPlusMode) {
                onNextToPlay(1)
                viewModel.onNextToPlusModeComplete()
            }
        })

        viewModel.eventNextToMinusMode.observe(viewLifecycleOwner, Observer { eventNextToMinusMode ->
            if (eventNextToMinusMode) {
                onNextToPlay(2)
                viewModel.onNextToMinusModeComplete()
            }
        })

        viewModel.eventNextToMultipliedMode.observe(viewLifecycleOwner, Observer { eventNextToMultipliedMode ->
            if (eventNextToMultipliedMode) {
                onNextToPlay(3)
                viewModel.onNextToMultipliedModeComplete()
            }
        })

        viewModel.eventNextToDivideMode.observe(viewLifecycleOwner, Observer { eventNextToDivideMode ->
            if (eventNextToDivideMode) {
                onNextToPlay(4)
                viewModel.onNextToDivideModeComplete()
            }
        })

        binding.menuViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(MenuFragmentDirections.actionMenuFragmentToMainFragment())
        }

        return binding.root
    }


    private fun onNextToPlay (menu: Int) {
        view?.findNavController()?.navigate(
            MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                viewModel.player.value!!.getPlayerId(),
                menu
            )
        )
    }

}