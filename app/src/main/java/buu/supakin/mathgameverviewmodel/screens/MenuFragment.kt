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
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentMainBinding
import buu.supakin.mathgameverviewmodel.databinding.FragmentMenuBinding
import buu.supakin.mathgameverviewmodel.models.GameViewModel


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_menu, container, false)

        gameViewModel = GameViewModel(
            MenuFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
            MenuFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
        )

//        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            btnPlusMode.setOnClickListener {
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        gameViewModel!!.correctScore.value?:0,
                        gameViewModel!!.inCorrectScore.value?:0,
                        1
                    )
                )
            }

            btnMinusMode.setOnClickListener {
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        gameViewModel!!.correctScore.value?:0,
                        gameViewModel!!.inCorrectScore.value?:0,
                        2
                    )
                )
            }

            btnMultipliedMode.setOnClickListener {
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        gameViewModel!!.correctScore.value?:0,
                        gameViewModel!!.inCorrectScore.value?:0,
                        3
                    )
                )
            }

            btnDivideMode.setOnClickListener {
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        gameViewModel!!.correctScore.value?:0,
                        gameViewModel!!.inCorrectScore.value?:0,
                        4
                    )
                )
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                MenuFragmentDirections.actionMenuFragmentToMainFragment(
                    gameViewModel!!.correctScore.value?:0,
                    gameViewModel!!.inCorrectScore.value?:0
                )
            )
        }


        return binding.root
    }

}