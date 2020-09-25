package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentPlayBinding
import buu.supakin.mathgameverviewmodel.models.GameViewModel
import buu.supakin.mathgameverviewmodel.models.QuestionModel


class PlayFragment : Fragment() {
    private var realAnswer = 0
    private var btnArray = arrayListOf<Button>()
    private lateinit var binding: FragmentPlayBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var questionModel: QuestionModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)

        gameViewModel = GameViewModel(
            PlayFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
            PlayFragmentArgs.fromBundle(requireArguments()).scoreInCorrect,
            PlayFragmentArgs.fromBundle(requireArguments()).menu
        )

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        questionModel = QuestionModel(gameViewModel.menu.value?:0)
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply{
            btnArray = arrayListOf(
                btnAnswer1,
                btnAnswer2,
                btnAnswer3,
                btnAnswer4
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                PlayFragmentDirections.actionPlayFragmentToMenuFragment(
                    gameViewModel.correctScore.value?:0,
                    gameViewModel.inCorrectScore.value?:0,
                )
            )
        }

        this.play()

        return binding.root
    }

    private fun play() {
        this.setAllText()
        this.setAllButton()
    }

    private fun setAllText () {
        binding.apply {
            txtNumber1.text = questionModel.num1.toString()
            txtNumber2.text = questionModel.num2.toString()
            txtOperator.text = questionModel.operator
            txtGuideLine.text = questionModel.guideline
        }
    }

    private fun setAllButton () {
        binding.apply{
            btnArray = arrayListOf(
                btnAnswer1,
                btnAnswer2,
                btnAnswer3,
                btnAnswer4
            )
        }
        for ((index, btn) in btnArray.withIndex()) {
            btn.text = questionModel.answerArray[index].toString()
            btn.setOnClickListener { this.getResult(btn.text.toString().toInt()) }
        }
    }



    private fun getResult(answer: Int)  {
        val result = if (questionModel.getResult(answer)) {
            gameViewModel.onCorrect()
            true
        } else {
            gameViewModel.onInCorrect()
            false
        }

        view?.findNavController()?.navigate(
            PlayFragmentDirections.actionPlayFragmentToResultFragment(
                gameViewModel.correctScore.value?:0,
                gameViewModel.inCorrectScore.value?:0,
                questionModel.menu,
                result
            )
        )
    }


}