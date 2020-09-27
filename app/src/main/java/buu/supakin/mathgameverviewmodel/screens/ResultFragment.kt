package buu.supakin.mathgameverviewmodel.screens

import android.annotation.SuppressLint
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
import buu.supakin.mathgameverviewmodel.databinding.FragmentResultBinding
import buu.supakin.mathgameverviewmodel.viewmodelfactories.GameViewModelFactory
import buu.supakin.mathgameverviewmodel.viewmodels.GameViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var  gameViewModelFactory: GameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        gameViewModelFactory = GameViewModelFactory(
            ResultFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
            ResultFragmentArgs.fromBundle(requireArguments()).scoreInCorrect,
            ResultFragmentArgs.fromBundle(requireArguments()).menu,
            ResultFragmentArgs.fromBundle(requireArguments()).result
        )

        gameViewModel = ViewModelProvider(this, gameViewModelFactory).get(GameViewModel::class.java)
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                ResultFragmentDirections.actionResultFragmentToPlayFragment(
                    gameViewModel!!.correctScore.value?:0,
                    gameViewModel!!.inCorrectScore.value?:0,
                    gameViewModel!!.menu.value?:0
                )
            )
        }

        this.init()
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    private fun init() {
        binding.apply {
            clResult.setBackgroundColor(if (gameViewModel!!.result.value != false) resources.getColor(R.color.colorSuccess)  else resources.getColor(
                R.color.colorDanger
            ))
            btnNext.setBackgroundResource(if (gameViewModel!!.result.value != false) R.drawable.btn_rounded_success else R.drawable.btn_rounded_danger)
            txtSummaryScore.text = getSummaryScore(if (gameViewModel!!.result.value != false) gameViewModel!!.correctScore.value?:0 else gameViewModel!!.inCorrectScore.value?:0)
            imgResult.setImageResource(if (gameViewModel!!.result.value != false) R.drawable.correct else R.drawable.incorrect)

            btnNext.setOnClickListener {
                view?.findNavController()?.navigate(
                    ResultFragmentDirections.actionResultFragmentToPlayFragment(
                        gameViewModel!!.correctScore.value?:0,
                        gameViewModel!!.inCorrectScore.value?:0,
                        gameViewModel!!.menu.value?:0
                    )
                )
            }
        }

    }

    private fun getSummaryScore (score: Int): String {
        return if (gameViewModel.result.value != false) getString(R.string.total_correct, score) else getString(R.string.total_incorrect, score)
    }

}