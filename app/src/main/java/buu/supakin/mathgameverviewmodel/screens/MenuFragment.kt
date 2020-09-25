package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.MenuFragmentArgs
import buu.supakin.mathgameverviewmodel.MenuFragmentDirections
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentMenuBinding



class MenuFragment : Fragment() {
    private var scoreCorrect = 0
    private var scoreInCorrect = 0
    private var menu = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater,
            R.layout.fragment_menu, container, false)

        scoreCorrect = MenuFragmentArgs.fromBundle(requireArguments()).scoreCorrect
        scoreInCorrect = MenuFragmentArgs.fromBundle(requireArguments()).scoreInCorrect

        binding.apply {

            txtScoreCorrect.text = scoreCorrect.toString()
            txtScoreInCorrect.text = scoreInCorrect.toString()

            btnPlusMode.setOnClickListener {
                menu = 1
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        scoreCorrect,
                        scoreInCorrect,
                        menu
                    )
                )
            }

            btnMinusMode.setOnClickListener {
                menu = 2
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        scoreCorrect,
                        scoreInCorrect,
                        menu
                    )
                )
            }

            btnMultipliedMode.setOnClickListener {
                menu = 3
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        scoreCorrect,
                        scoreInCorrect,
                        menu
                    )
                )
            }

            btnDivideMode.setOnClickListener {
                menu = 4
                view?.findNavController()?.navigate(
                    MenuFragmentDirections.actionMenuFragmentToPlayFragment(
                        scoreCorrect,
                        scoreInCorrect,
                        menu
                    )
                )
            }



        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                MenuFragmentDirections.actionMenuFragmentToMainFragment(
                    scoreCorrect,
                    scoreInCorrect
                )
            )
        }


        return binding.root
    }

}