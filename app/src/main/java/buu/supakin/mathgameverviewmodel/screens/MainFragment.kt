package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.MainFragmentArgs
import buu.supakin.mathgameverviewmodel.MainFragmentDirections
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentMainBinding
import buu.supakin.mathgameverviewmodel.models.GameViewModel
import kotlin.system.exitProcess

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        gameViewModel = GameViewModel(
            MainFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
            MainFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
        )

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.btnPlay.setOnClickListener {
//            view?.findNavController()?.navigate(
////                MainFragmentDirections.actionMainFragmentToMenuFragment(
////                    scoreCorrect,
////                    scoreInCorrect
////                )
////            )
        }
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            exitProcess(0)
        }

        return binding.root
    }

}