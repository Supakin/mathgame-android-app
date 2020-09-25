package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.MainFragmentArgs
import buu.supakin.mathgameverviewmodel.MainFragmentDirections
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentMainBinding
import kotlin.system.exitProcess

class MainFragment : Fragment() {

    private var scoreCorrect = 0
    private var scoreInCorrect = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main, container, false)
        scoreCorrect = MainFragmentArgs.fromBundle(requireArguments()).scoreCorrect
        scoreInCorrect = MainFragmentArgs.fromBundle(requireArguments()).scoreInCorrect

        binding.btnPlay.setOnClickListener {
            view?.findNavController()?.navigate(
                MainFragmentDirections.actionMainFragmentToMenuFragment(
                    scoreCorrect,
                    scoreInCorrect
                )
            )
        } 

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            exitProcess(0)
        }

        return binding.root
    }

}